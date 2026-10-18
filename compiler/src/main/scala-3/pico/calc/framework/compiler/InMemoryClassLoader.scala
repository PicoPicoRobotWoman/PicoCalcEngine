package pico.calc.framework.compiler

final class InMemoryClassLoader(classFileManager: InMemoryClassFileManager,
                                parent: ClassLoader = Thread.currentThread().getContextClassLoader) extends ClassLoader(parent) {
  override protected def findClass(name: String): Class[?] =
    classFileManager.get(name) match
      case Some(bytecode) =>
        defineClass(name, bytecode, 0, bytecode.length)
      case None =>
        super.findClass(name)
}
