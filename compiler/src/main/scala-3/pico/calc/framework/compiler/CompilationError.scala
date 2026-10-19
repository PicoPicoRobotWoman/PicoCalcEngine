package pico.calc.framework.compiler

/** Represents an error that occurs during Scala source compilation.
  *
  * <p>The exception contains a summary message and optional compiler diagnostics. When diagnostics
  * are available, they are appended to the message to provide additional details about the
  * compilation failure.
  *
  * @param message
  *   the summary of the compilation error
  * @param diagnostics
  *   the compiler diagnostics describing the compilation failure, or an empty sequence if no
  *   diagnostics are available
  * @param cause
  *   the underlying cause of the compilation error, or {@code null} if no cause exists
  */
final case class CompilationError(
  message: String,
  diagnostics: Seq[String] = Seq.empty,
  cause: Throwable = null
) extends RuntimeException(
      if diagnostics.isEmpty then message
      else s"$message:\n${diagnostics.mkString("\n")}",
      cause
    )
