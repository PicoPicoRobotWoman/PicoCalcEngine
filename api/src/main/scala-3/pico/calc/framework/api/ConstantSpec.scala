package pico.calc.framework.api

/** Defines a specification for a constant value.
  *
  * <p>A constant specification provides a supplier that returns the constant value when evaluated.
  */
trait ConstantSpec[T] {

  /** Returns a supplier of the constant value.
    *
    * @return
    *   a function that produces the constant value
    */
  def get(): () => T
}
