package pico.calc.framework.function.logic

import pico.calc.framework.api.{Default, FunctionSpec, Name, Contract}

@Name("&&")
@Contract(returnType = "Boolean", argumentTypes = Array("Boolean", "Boolean"))
@Default
final class And1 extends FunctionSpec[Boolean] {
  def get(): List[Any] => Boolean =
    args => args.head.asInstanceOf[Boolean] && args(1).asInstanceOf[Boolean]
}

@Name("&")
@Contract(returnType = "Boolean", argumentTypes = Array("Boolean", "Boolean"))
@Default
final class And2 extends FunctionSpec[Boolean] {
  def get(): List[Any] => Boolean =
    args => args.head.asInstanceOf[Boolean] && args(1).asInstanceOf[Boolean]
}

@Name("and")
@Contract(returnType = "Boolean", argumentTypes = Array("Boolean", "Boolean"))
@Default
final class And3 extends FunctionSpec[Boolean] {
  def get(): List[Any] => Boolean =
    args => args.head.asInstanceOf[Boolean] && args(1).asInstanceOf[Boolean]
}
