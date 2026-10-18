package pico.calc.framework.compiler

trait ScalaCompiler {
  def compile(className: String, source: String): Either[CompilationError, CompilationResult]
}
