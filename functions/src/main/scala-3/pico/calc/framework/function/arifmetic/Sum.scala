package pico.calc.framework.function.arifmetic

import pico.calc.framework.api.{Default, FunctionSpec, Name, Contract}

@Name("+")
@Contract(returnType = "Int", argumentTypes = Array("Int", "Int"))
final class IntSumFuncion extends FunctionSpec[Int] {
  override def get(): List[Any] => Int =
    args => args.head.asInstanceOf[Int] + args(1).asInstanceOf[Int]
}

@Name("+")
@Contract(returnType = "BigDecimal", argumentTypes = Array("BigDecimal", "BigDecimal"))
final class BigDecimalSumFunction extends FunctionSpec[BigDecimal] {
  override def get(): List[Any] => BigDecimal =
    args => args.head.asInstanceOf[BigDecimal] + args(1).asInstanceOf[BigDecimal]
}

@Name("+")
@Contract(returnType = "Double", argumentTypes = Array("Double", "Double"))
final class DoubleSumFunction extends FunctionSpec[Double] {
  override def get(): List[Any] => Double =
    args => args.head.asInstanceOf[Double] + args(1).asInstanceOf[Double]
}

@Name("+")
@Contract(returnType = "Int", argumentTypes = Array("Int", "Int"))
final class IntSumFunction extends FunctionSpec[Int] {
  override def get(): List[Any] => Int =
    args => args.head.asInstanceOf[Int] + args(1).asInstanceOf[Int]
}

@Name("+")
@Contract(returnType = "Int", argumentTypes = Array("Int", "Int"))
final class TextSumFunction extends FunctionSpec[String] {
  override def get(): List[Any] => String =
    args => args.head.asInstanceOf[String] + args(1).asInstanceOf[String]
}

@Name("+")
@Contract(returnType = "Any", argumentTypes = Array("Any", "Any"))
@Default
final class AnySumFunction extends FunctionSpec[Any] {
  override def get(): List[Any] => Any = args => {
    val a = args.head
    val b = args(1)

    (a, b) match {
      case (x: Int, y: Int)               => x + y
      case (x: Long, y: Long)             => x + y
      case (x: Double, y: Double)         => x + y
      case (x: Float, y: Float)           => x + y
      case (x: BigDecimal, y: BigDecimal) => x + y
      case (x: BigInt, y: BigInt)         => x + y
      case (x: String, y: String)         => x + y
      case _                              => throw new IllegalArgumentException(
          s"Unsupported numeric operands: ${a.getClass.getName}, ${b.getClass.getName}"
        )
    }
  }
}
