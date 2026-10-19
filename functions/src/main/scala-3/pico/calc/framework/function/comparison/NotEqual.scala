package pico.calc.framework.function.comparison

import pico.calc.framework.api.{Default, FunctionSpec, Name, Contract}

@Name("!=")
@Contract(returnType = "Boolean", argumentTypes = Array("Any", "Any"))
@Default
final class NotEquals0 extends FunctionSpec[Any] {
  def get(): List[Any] => Any =
    args => args(0) == args(1)
}

@Name("<>")
@Contract(returnType = "Boolean", argumentTypes = Array("Any", "Any"))
@Default
final class NotEquals1 extends FunctionSpec[Any] {
  def get(): List[Any] => Any =
    args => args.head == args(1)
}

@Name("not_equals")
@Contract(returnType = "Boolean", argumentTypes = Array("Any", "Any"))
@Default
final class NotEquals2 extends FunctionSpec[Any] {

  def get(): List[Any] => Any =
    args => args.head == args(1)
}

@Name("not_eq")
@Contract(returnType = "Boolean", argumentTypes = Array("Any", "Any"))
@Default
final class NotEquals3 extends FunctionSpec[Any] {

  def get(): List[Any] => Any =
    args => args.head == args(1)
}
