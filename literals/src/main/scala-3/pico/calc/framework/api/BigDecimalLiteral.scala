package pico.calc.framework.api

@LiteralMeta(name = "BigDecimal")
final class BigDecimalLiteral extends LiteralSpec {
  override def get(): String => Any = BigDecimal(_)
}
