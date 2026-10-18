package pico.calc.framework.literal

import pico.calc.framework.api.{LiteralSpec, Name}

@Name("Text")
final class TextLiteral extends LiteralSpec {
  override def get(): String => String = identity
}
