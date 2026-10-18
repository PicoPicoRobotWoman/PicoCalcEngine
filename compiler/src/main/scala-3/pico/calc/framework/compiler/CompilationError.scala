package pico.calc.framework.compiler

final class CompilationError(message: String,
                             val diagnostics: Seq[String] = Seq.empty,
                             cause: Throwable = null
                            ) extends RuntimeException(
  if diagnostics.isEmpty then message
  else s"$message:\n${diagnostics.mkString("\n")}",
  cause
)
