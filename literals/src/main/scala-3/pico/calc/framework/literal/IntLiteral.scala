package pico.calc.framework.literal

import pico.calc.framework.api.{LiteralSpec, Name, Contract}

@Name("Int")
@Contract(returnType = "Int")
final class IntLiteral extends LiteralSpec[Int] {
  override def get(): String => Int = _.toInt
}
