package pico.calc.framework.compiler

import dotty.tools.io.VirtualDirectory

final class ScalaCompilerImpl {

  def compile(source: String, className: String): Class[?] =
    require(source.trim.nonEmpty, "Source code must not be empty")
    require(className.trim.nonEmpty, "Class name must not be empty")

    val output = new InMemoryScalaDriver().compile(
      source = source,
      classpath = System.getProperty("java.class.path")
    )

    val classes = new InMemoryClassFileManager
    collectClasses(output, classes)

    val parent =
      Option(Thread.currentThread().getContextClassLoader)
        .getOrElse(getClass.getClassLoader)

    val classLoader = new InMemoryClassLoader(classes, parent)

    try
      classLoader.loadClass(className)
    catch
      case ex: ClassNotFoundException =>
        throw new CompilationError(
          s"Compiled class '$className' was not found in memory",
          cause = ex
        )

  private def collectClasses(directory: VirtualDirectory,
                             manager: InMemoryClassFileManager,
                             prefix: String = ""): Unit =
    directory.iterator.foreach:
      case subdirectory: VirtualDirectory =>
        collectClasses(
          subdirectory,
          manager,
          prefix + subdirectory.name + "/"
        )

      case file if file.name.endsWith(".class") =>
        val className =
          (prefix + file.name.stripSuffix(".class"))
            .replace('/', '.')

        val input = file.input

        try
          manager.put(className, input.readAllBytes())
        finally
          input.close()

      case _ =>
        ()

}