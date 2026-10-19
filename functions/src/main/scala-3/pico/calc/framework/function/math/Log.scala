package pico.calc.framework.function.math

import pico.calc.framework.api.{Contract, Default, FunctionSpec, Name}

@Name("Log")
@Contract(returnType = "Int", argumentTypes = Array("Int", "Int"))
@Default
final class LogInt extends FunctionSpec[Int] {
  override def get(): List[Any] => Int =
    args =>
      (math.log(args(1).asInstanceOf[Int].toDouble) /
        math.log(args.head.asInstanceOf[Int].toDouble)).toInt
}

@Name("Log")
@Contract(returnType = "Long", argumentTypes = Array("Long", "Long"))
@Default
final class LogLong extends FunctionSpec[Long] {
  override def get(): List[Any] => Long =
    args =>
      (math.log(args(1).asInstanceOf[Long].toDouble) /
        math.log(args.head.asInstanceOf[Long].toDouble)).toLong
}

@Name("Log")
@Contract(returnType = "Float", argumentTypes = Array("Float", "Float"))
@Default
final class LogFloat extends FunctionSpec[Float] {
  override def get(): List[Any] => Float =
    args =>
      (math.log(args(1).asInstanceOf[Float].toDouble) /
        math.log(args.head.asInstanceOf[Float].toDouble)).toFloat
}

@Name("Log")
@Contract(returnType = "Double", argumentTypes = Array("Double", "Double"))
@Default
final class LogDouble extends FunctionSpec[Double] {
  override def get(): List[Any] => Double =
    args =>
      math.log(args(1).asInstanceOf[Double]) /
        math.log(args.head.asInstanceOf[Double])
}

@Name("Log")
@Contract(returnType = "BigInt", argumentTypes = Array("BigInt", "BigInt"))
@Default
final class LogBigInt extends FunctionSpec[BigInt] {
  override def get(): List[Any] => BigInt =
    args =>
      BigInt(
        (math.log(args(1).asInstanceOf[BigInt].toDouble) /
          math.log(args.head.asInstanceOf[BigInt].toDouble)).toLong
      )
}

@Name("Log")
@Contract(returnType = "BigDecimal", argumentTypes = Array("BigDecimal", "BigDecimal"))
@Default
final class LogBigDecimal extends FunctionSpec[BigDecimal] {
  override def get(): List[Any] => BigDecimal =
    args =>
      BigDecimal(
        math.log(args(1).asInstanceOf[BigDecimal].toDouble) /
          math.log(args.head.asInstanceOf[BigDecimal].toDouble)
      )
}
