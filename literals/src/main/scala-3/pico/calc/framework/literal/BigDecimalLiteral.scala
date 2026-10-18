package pico.calc.framework.literal

import pico.calc.framework.api.{LiteralSpec, Name}

@Name("BigDecimal")
final class BigDecimalLiteral extends LiteralSpec {
  override def get(): String => Any = BigDecimal(_)
}
