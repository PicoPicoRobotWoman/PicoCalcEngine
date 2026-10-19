package pico.calc.framework.function.comparison

import pico.calc.framework.api.{Default, FunctionSpec, Name, Contract}

@Name("==")
@Contract(returnType = "Boolean", argumentTypes = Array("Any", "Any"))
@Default
final class Equals1 extends FunctionSpec[Any] {
  def get(): List[Any] => Any =
    args => args.head == args(1)
}

@Name("equals")
@Contract(returnType = "Boolean", argumentTypes = Array("Any", "Any"))
@Default
final class Equals2 extends FunctionSpec[Any] {

  def get(): List[Any] => Any =
    args => args.head == args(1)
}

@Name("eq")
@Contract(returnType = "Boolean", argumentTypes = Array("Any", "Any"))
@Default
final class Equals3 extends FunctionSpec[Any] {

  def get(): List[Any] => Any =
    args => args.head == args(1)
}
