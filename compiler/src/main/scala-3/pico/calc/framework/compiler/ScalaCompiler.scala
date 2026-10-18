package pico.calc.framework.compiler

import dotty.tools.io.{AbstractFile, VirtualDirectory}

/** Compiles Scala source code in memory and loads the resulting class.
  *
  * <p>This implementation invokes the Scala compiler, collects generated class bytecode into an
  * in-memory class file manager, and loads the requested class using a dedicated class loader. No
  * compiled class files are written to disk.
  */
object ScalaCompiler {

  def apply(): (source: String, className: String) => Class[?] = compile

  private def compile(source: String, className: String): Class[?] = {
    require(source.trim.nonEmpty, "Source code must not be empty")
    require(className.trim.nonEmpty, "Class name must not be empty")

    val output = InMemoryScalaDriver.compile(
      source = source,
      classpath = System.getProperty("java.class.path")
    )

    val parent =
      Option(Thread.currentThread().getContextClassLoader)
        .getOrElse(getClass.getClassLoader)

    val classFile = findClassFile(output)
      .getOrElse {
        throw new CompilationError("No compiled class found in memory")
      }

    val input = classFile.input
    val bytecode =
      try
        input.readAllBytes()
      finally
        input.close()

    val classLoader = new ClassLoader(parent) {
      override protected def findClass(name: String): Class[?] = {
        if (name != className) {
          throw new ClassNotFoundException(name)
        }

        defineClass(name, bytecode, 0, bytecode.length)
      }
    }

    classLoader.loadClass(className)
  }

  private def findClassFile(directory: VirtualDirectory): Option[AbstractFile] =
    directory.iterator
      .map {
        case subdirectory: VirtualDirectory =>
          findClassFile(subdirectory)
        case file if file.name.endsWith(".class") =>
          Some(file)
        case _ =>
          None
      }
      .collectFirst { case Some(file) => file }

}
