package pico.calc.framework.api

/** Defines a specification for a function supported by the expression engine.
  *
  * <p>A function specification provides an implementation that accepts a list of argument values
  * and returns the result of the function. Function implementations can be registered with a
  * function dispatcher and resolved by name when processing expressions.
  *
  * @see
  *   FunctionMeta
  */
trait FunctionSpec {

  /** Returns the function implementation.
    *
    * <p>The returned function receives the evaluated arguments as a list of values and produces the
    * result of the computation.
    *
    * @return
    *   a function that accepts argument values and returns the result
    */
  def get(): List[Any] => Any
}
