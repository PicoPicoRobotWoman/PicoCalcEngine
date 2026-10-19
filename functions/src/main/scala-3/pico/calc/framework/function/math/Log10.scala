package pico.calc.framework.function.math

import pico.calc.framework.api.{Contract, Default, FunctionSpec, Name}

@Name("Log10")
@Contract(returnType = "Int", argumentTypes = Array("Int"))
@Default
final class Log10Int extends FunctionSpec[Int] {
  override def get(): List[Any] => Int =
    args => math.log10(args.head.asInstanceOf[Int].toDouble).toInt
}

@Name("Log10")
@Contract(returnType = "Long", argumentTypes = Array("Long"))
@Default
final class Log10Long extends FunctionSpec[Long] {
  override def get(): List[Any] => Long =
    args => math.log10(args.head.asInstanceOf[Long].toDouble).toLong
}

@Name("Log10")
@Contract(returnType = "Float", argumentTypes = Array("Float"))
@Default
final class Log10Float extends FunctionSpec[Float] {
  override def get(): List[Any] => Float =
    args => math.log10(args.head.asInstanceOf[Float].toDouble).toFloat
}

@Name("Log10")
@Contract(returnType = "Double", argumentTypes = Array("Double"))
@Default
final class Log10Double extends FunctionSpec[Double] {
  override def get(): List[Any] => Double =
    args => math.log10(args.head.asInstanceOf[Double])
}

@Name("Log10")
@Contract(returnType = "BigInt", argumentTypes = Array("BigInt"))
@Default
final class Log10BigInt extends FunctionSpec[BigInt] {
  override def get(): List[Any] => BigInt =
    args =>
      BigInt(
        math.log10(args.head.asInstanceOf[BigInt].toDouble).toLong
      )
}

@Name("Log10")
@Contract(returnType = "BigDecimal", argumentTypes = Array("BigDecimal"))
@Default
final class Log10BigDecimal extends FunctionSpec[BigDecimal] {
  override def get(): List[Any] => BigDecimal =
    args =>
      BigDecimal(
        math.log10(args.head.asInstanceOf[BigDecimal].toDouble)
      )
}
