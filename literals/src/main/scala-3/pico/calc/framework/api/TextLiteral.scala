package pico.calc.framework.api

@LiteralMeta(name = "Text")
final class TextLiteral extends LiteralSpec {
  override def get(): String => String = identity
}
