package pico.calc.framework.api

trait ScalaCompiler:
  def compile(className: String, source: String): Either[CompilationError, CompilationResult]
