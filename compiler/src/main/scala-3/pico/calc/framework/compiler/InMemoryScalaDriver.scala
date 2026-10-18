package pico.calc.framework.compiler

import dotty.tools.dotc.Driver
import dotty.tools.io.{VirtualDirectory, VirtualFile}

import java.nio.charset.StandardCharsets

/** Scala 3 compiler driver that compiles source code in memory without writing source files or
  * compilation output to disk.
  */
private[compiler] object InMemoryScalaDriver extends Driver {

  override protected def sourcesRequired: Boolean = false

  /** Compiles Scala source code in memory.
    *
    * @param source
    *   Scala source code to compile
    * @param classpath
    *   classpath required for compilation
    * @return
    *   virtual directory containing the compiled output
    * @throws CompilationError
    *   if initialization or compilation fails
    */
  def compile(
    source: String,
    classpath: String
  ): VirtualDirectory =

    val args = Array("-classpath", classpath)

    setup(args, initCtx) match
      case None =>
        throw CompilationError(
          "Could not initialize Scala compiler"
        )

      case Some((_, initialContext)) =>
        val output = new VirtualDirectory("pico-memory-output")
        val context = initialContext.fresh

        context.setSetting(context.settings.outputDir, output)

        val sourceFile = new VirtualFile(
          "GeneratedSource.scala",
          source.getBytes(StandardCharsets.UTF_8)
        )

        val reporter =
          doCompile(
            newCompiler(using context),
            List(sourceFile)
          )(using context)

        if reporter.hasErrors then
          throw CompilationError(
            "Scala compilation failed; see compiler diagnostics above"
          )

        output
}
