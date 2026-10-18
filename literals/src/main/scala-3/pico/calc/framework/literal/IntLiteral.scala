package pico.calc.framework.literal

import pico.calc.framework.api.{LiteralSpec, Name}

@Name("Int")
final class IntLiteral extends LiteralSpec {
  override def get(): String => Int = _.toInt
}
