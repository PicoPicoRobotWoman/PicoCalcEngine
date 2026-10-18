package pico.calc.framework.api

@LiteralMeta(name = "null")
final class NullLiteral extends LiteralSpec {
  override def get(): String => Any = v => null
}
