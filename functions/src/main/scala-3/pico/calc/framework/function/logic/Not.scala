package pico.calc.framework.function.logic

import pico.calc.framework.api.{Default, FunctionSpec, Name, Contract}

@Name("!")
@Contract(returnType = "Boolean", argumentTypes = Array("Boolean"))
@Default
final class Not1 extends FunctionSpec[Boolean] {
  override def get(): List[Any] => Boolean =
    args => !args.head.asInstanceOf[Boolean]
}

@Name("not")
@Contract(returnType = "Boolean", argumentTypes = Array("Boolean"))
@Default
final class Not2 extends FunctionSpec[Boolean] {
  override def get(): List[Any] => Boolean =
    args => !args.head.asInstanceOf[Boolean]
}
