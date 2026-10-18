package pico.calc.framework.literal

import pico.calc.framework.api.{LiteralMeta, LiteralSpec}

@LiteralMeta(name = "BigDecimal")
final class BigDecimalLiteral extends LiteralSpec {
  override def get(): String => Any = BigDecimal(_)
}
