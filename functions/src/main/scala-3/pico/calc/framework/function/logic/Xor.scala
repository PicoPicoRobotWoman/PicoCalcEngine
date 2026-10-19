package pico.calc.framework.function.logic

import pico.calc.framework.api.{Default, FunctionSpec, Name, Contract}

@Name("^")
@Contract(returnType = "Boolean", argumentTypes = Array("Boolean", "Boolean"))
@Default
final class Xor1 extends FunctionSpec[Boolean] {
  override def get(): List[Any] => Boolean =
    args => args(0).asInstanceOf[Boolean] ^ args(1).asInstanceOf[Boolean]
}

@Name("xor")
@Contract(returnType = "Boolean", argumentTypes = Array("Boolean", "Boolean"))
@Default
final class Xor2 extends FunctionSpec[Boolean] {
  override def get(): List[Any] => Boolean =
    args => args(0).asInstanceOf[Boolean] ^ args(1).asInstanceOf[Boolean]
}
