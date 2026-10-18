package pico.calc.framework.compiler

/** Represents the result of a successful compilation.
  *
  * <p>The result contains the compiled class files as byte arrays, indexed by their fully qualified
  * class names. These byte arrays can be used to load the compiled classes at runtime.
  *
  * @param classes
  *   a map from fully qualified class names to their compiled bytecode
  */
final case class CompilationResult(classes: Map[String, Array[Byte]])
