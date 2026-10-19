package pico.calc.framework.function.math

import pico.calc.framework.api.{Contract, Default, FunctionSpec, Name}

@Name("Lg")
@Contract(returnType = "Int", argumentTypes = Array("Int"))
@Default
final class LgInt extends FunctionSpec[Int] {
  override def get(): List[Any] => Int =
    args => math.log10(args.head.asInstanceOf[Int].toDouble).toInt
}

@Name("Lg")
@Contract(returnType = "Long", argumentTypes = Array("Long"))
@Default
final class LgLong extends FunctionSpec[Long] {
  override def get(): List[Any] => Long =
    args => math.log10(args.head.asInstanceOf[Long].toDouble).toLong
}

@Name("Lg")
@Contract(returnType = "Float", argumentTypes = Array("Float"))
@Default
final class LgFloat extends FunctionSpec[Float] {
  override def get(): List[Any] => Float =
    args => math.log10(args.head.asInstanceOf[Float].toDouble).toFloat
}

@Name("Lg")
@Contract(returnType = "Double", argumentTypes = Array("Double"))
@Default
final class LgDouble extends FunctionSpec[Double] {
  override def get(): List[Any] => Double =
    args => math.log10(args.head.asInstanceOf[Double])
}

@Name("Lg")
@Contract(returnType = "BigInt", argumentTypes = Array("BigInt"))
@Default
final class LgBigInt extends FunctionSpec[BigInt] {
  override def get(): List[Any] => BigInt =
    args =>
      BigInt(
        math.log10(args.head.asInstanceOf[BigInt].toDouble).toLong
      )
}

@Name("Lg")
@Contract(returnType = "BigDecimal", argumentTypes = Array("BigDecimal"))
@Default
final class LgBigDecimal extends FunctionSpec[BigDecimal] {
  override def get(): List[Any] => BigDecimal =
    args =>
      BigDecimal(
        math.log10(args.head.asInstanceOf[BigDecimal].toDouble)
      )
}
