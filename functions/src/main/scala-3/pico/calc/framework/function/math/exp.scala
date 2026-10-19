package pico.calc.framework.function.math

import pico.calc.framework.api.{Contract, FunctionSpec, Name}

@Name("exp")
@Contract(returnType = "Int", argumentTypes = Array("Int"))
final class ExpInt extends FunctionSpec[Int] {
  override def get(): List[Any] => Int =
    args => math.exp(args.head.asInstanceOf[Int].toDouble).toInt
}

@Name("exp")
@Contract(returnType = "Long", argumentTypes = Array("Long"))
final class ExpLong extends FunctionSpec[Long] {
  override def get(): List[Any] => Long =
    args => math.exp(args.head.asInstanceOf[Long].toDouble).toLong
}

@Name("exp")
@Contract(returnType = "Float", argumentTypes = Array("Float"))
final class ExpFloat extends FunctionSpec[Float] {
  override def get(): List[Any] => Float =
    args => math.exp(args.head.asInstanceOf[Float].toDouble).toFloat
}

@Name("exp")
@Contract(returnType = "Double", argumentTypes = Array("Double"))
final class ExpDouble extends FunctionSpec[Double] {
  override def get(): List[Any] => Double =
    args => math.exp(args.head.asInstanceOf[Double])
}

@Name("exp")
@Contract(returnType = "BigInt", argumentTypes = Array("BigInt"))
final class ExpBigInt extends FunctionSpec[BigInt] {
  override def get(): List[Any] => BigInt =
    args => BigInt(math.exp(args.head.asInstanceOf[BigInt].toDouble).toLong)
}

@Name("exp")
@Contract(returnType = "BigDecimal", argumentTypes = Array("BigDecimal"))
final class ExpBigDecimal extends FunctionSpec[BigDecimal] {
  override def get(): List[Any] => BigDecimal =
    args => BigDecimal(math.exp(args.head.asInstanceOf[BigDecimal].toDouble))
}
