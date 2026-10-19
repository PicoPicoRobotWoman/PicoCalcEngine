package pico.calc.framework.api

/** Defines a specification for a literal supported by the expression engine.
  *
  * <p>A literal specification provides an implementation that converts the textual representation
  * of a literal into its corresponding runtime value. Literal implementations can be registered
  * with a literal dispatcher and resolved by name when processing expressions.
  *
  * @see
  *   LiteralMeta
  */
trait LiteralSpec[T] {

  /** Returns the literal parsing function.
    *
    * <p>The returned function accepts the textual representation of a literal and converts it into
    * the corresponding runtime value.
    *
    * @return
    *   a function that parses a literal string and returns its value
    */
  def get(): String => T
}
