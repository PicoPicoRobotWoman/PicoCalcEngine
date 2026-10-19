package pico.calc.framework.function.math

import pico.calc.framework.api.{Contract, Default, FunctionSpec, Name}

@Name("Ln")
@Contract(returnType = "Int", argumentTypes = Array("Int"))
@Default
final class LnInt extends FunctionSpec[Int] {
  override def get(): List[Any] => Int =
    args => math.log(args.head.asInstanceOf[Int].toDouble).toInt
}

@Name("Ln")
@Contract(returnType = "Long", argumentTypes = Array("Long"))
@Default
final class LnLong extends FunctionSpec[Long] {
  override def get(): List[Any] => Long =
    args => math.log(args.head.asInstanceOf[Long].toDouble).toLong
}

@Name("Ln")
@Contract(returnType = "Float", argumentTypes = Array("Float"))
@Default
final class LnFloat extends FunctionSpec[Float] {
  override def get(): List[Any] => Float =
    args => math.log(args.head.asInstanceOf[Float].toDouble).toFloat
}

@Name("Ln")
@Contract(returnType = "Double", argumentTypes = Array("Double"))
@Default
final class LnDouble extends FunctionSpec[Double] {
  override def get(): List[Any] => Double =
    args => math.log(args.head.asInstanceOf[Double])
}

@Name("Ln")
@Contract(returnType = "BigInt", argumentTypes = Array("BigInt"))
@Default
final class LnBigInt extends FunctionSpec[BigInt] {
  override def get(): List[Any] => BigInt =
    args =>
      BigInt(
        math.log(args.head.asInstanceOf[BigInt].toDouble).toLong
      )
}

@Name("Ln")
@Contract(returnType = "BigDecimal", argumentTypes = Array("BigDecimal"))
@Default
final class LnBigDecimal extends FunctionSpec[BigDecimal] {
  override def get(): List[Any] => BigDecimal =
    args =>
      BigDecimal(
        math.log(args.head.asInstanceOf[BigDecimal].toDouble)
      )
}
