package pico.calc.framework.function

import pico.calc.framework.api.{Name, FunctionSpec}

@Name("+")
final class SumarFuncion extends FunctionSpec {
  override def get(): List[Any] => Any =
    args =>
      args(0) match {
        case a: Int =>
          args(1) match {
            case b: Int => a + b
            case _      => throw new IllegalArgumentException("Se esperaba un Int")
          }
        case _ => throw new IllegalArgumentException("Se esperaba un Int")
      }
}
