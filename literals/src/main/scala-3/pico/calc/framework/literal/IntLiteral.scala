package pico.calc.framework.literal

import pico.calc.framework.api.{LiteralMeta, LiteralSpec}

@LiteralMeta(name = "Int")
final class IntLiteral extends LiteralSpec {
  override def get(): String => Int = _.toInt
}
