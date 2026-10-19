package pico.calc.framework.literal

import pico.calc.framework.api.{Imports, LiteralSpec, Name, Contract}

@Name("BigDecimal")
@Contract(returnType = "BigDecimal")
final class BigDecimalLiteral extends LiteralSpec[BigDecimal] {
  override def get(): String => BigDecimal = BigDecimal(_)
}
