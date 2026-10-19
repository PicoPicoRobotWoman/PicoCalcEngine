package pico.calc.framework.function.comparison

import pico.calc.framework.api.{Default, FunctionSpec, Name, Contract}

@Name("<")
@Contract(returnType = "Boolean", argumentTypes = Array("Int", "Int"))
final class IntLessThanFunction extends FunctionSpec[Boolean] {
  override def get(): List[Any] => Boolean =
    args => args.head.asInstanceOf[Int] < args(1).asInstanceOf[Int]
}

@Name("<")
@Contract(returnType = "Boolean", argumentTypes = Array("Long", "Long"))
final class LongLessThanFunction extends FunctionSpec[Boolean] {
  override def get(): List[Any] => Boolean =
    args => args.head.asInstanceOf[Long] < args(1).asInstanceOf[Long]
}

@Name("<")
@Contract(returnType = "Boolean", argumentTypes = Array("Double", "Double"))
final class DoubleLessThanFunction extends FunctionSpec[Boolean] {
  override def get(): List[Any] => Boolean =
    args => args.head.asInstanceOf[Double] < args(1).asInstanceOf[Double]
}

@Name("<")
@Contract(returnType = "Boolean", argumentTypes = Array("Float", "Float"))
final class FloatLessThanFunction extends FunctionSpec[Boolean] {
  override def get(): List[Any] => Boolean =
    args => args.head.asInstanceOf[Float] < args(1).asInstanceOf[Float]
}

@Name("<")
@Contract(returnType = "Boolean", argumentTypes = Array("BigDecimal", "BigDecimal"))
final class BigDecimalLessThanFunction extends FunctionSpec[Boolean] {
  override def get(): List[Any] => Boolean =
    args => args.head.asInstanceOf[BigDecimal] < args(1).asInstanceOf[BigDecimal]
}

@Name("<")
@Contract(returnType = "Boolean", argumentTypes = Array("BigInt", "BigInt"))
final class BigIntLessThanFunction extends FunctionSpec[Boolean] {
  override def get(): List[Any] => Boolean =
    args => args.head.asInstanceOf[BigInt] < args(1).asInstanceOf[BigInt]
}

@Name("<")
@Contract(returnType = "String", argumentTypes = Array("String", "String"))
final class TextThanFunction extends FunctionSpec[Boolean] {
  override def get(): List[Any] => Boolean =
    args => args.head.asInstanceOf[String].length < args(1).asInstanceOf[String].length
}

@Name("<")
@Contract(returnType = "Boolean", argumentTypes = Array("Any", "Any"))
@Default
final class NumericLessThanFunction extends FunctionSpec[Boolean] {
  override def get(): List[Any] => Boolean = args => {
    val a = args.head
    val b = args(1)

    (a, b) match {
      case (x: Int, y: Int)               => x < y
      case (x: Long, y: Long)             => x < y
      case (x: Double, y: Double)         => x < y
      case (x: Float, y: Float)           => x < y
      case (x: BigDecimal, y: BigDecimal) => x < y
      case (x: BigInt, y: BigInt)         => x < y
      case (x: String, y: String)         => x.length < y.length
      case _                              =>
        throw new IllegalArgumentException(
          s"Unsupported numeric operands: ${a.getClass.getName}, ${b.getClass.getName}"
        )
    }
  }
}
