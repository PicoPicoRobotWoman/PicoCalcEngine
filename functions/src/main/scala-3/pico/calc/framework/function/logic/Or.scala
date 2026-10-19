package pico.calc.framework.function.logic

import pico.calc.framework.api.{Default, FunctionSpec, Name, Contract}

@Name("||")
@Contract(returnType = "Boolean", argumentTypes = Array("Boolean", "Boolean"))
@Default
final class Or1 extends FunctionSpec[Boolean] {
  override def get(): List[Any] => Boolean =
    args => args.head.asInstanceOf[Boolean] || args(1).asInstanceOf[Boolean]
}

@Name("|")
@Contract(returnType = "Boolean", argumentTypes = Array("Boolean", "Boolean"))
@Default
final class Or2 extends FunctionSpec[Boolean] {
  override def get(): List[Any] => Boolean =
    args => args.head.asInstanceOf[Boolean] | args(1).asInstanceOf[Boolean]
}

@Name("or")
@Contract(returnType = "Boolean", argumentTypes = Array("Boolean", "Boolean"))
@Default
final class Or3 extends FunctionSpec[Boolean] {
  override def get(): List[Any] => Boolean =
    args => args.head.asInstanceOf[Boolean] || args(1).asInstanceOf[Boolean]
}
