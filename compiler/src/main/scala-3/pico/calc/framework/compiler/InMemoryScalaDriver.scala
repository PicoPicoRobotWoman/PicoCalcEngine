package pico.calc.framework.compiler

import dotty.tools.dotc.Driver
import dotty.tools.io.{VirtualDirectory, VirtualFile}

import java.nio.charset.StandardCharsets

private[compiler] object InMemoryScalaDriver extends Driver {

  override protected def sourcesRequired: Boolean = false

  def compile(
    source: String,
    classpath: String
  ): VirtualDirectory =

    val args = Array(
      "-classpath",
      classpath,
      "-color:never"
    )

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
