package pico.calc.framework.literal

import pico.calc.framework.api.{LiteralSpec, Name, Contract}

@Name("Text")
@Contract(returnType = "String")
final class TextLiteral extends LiteralSpec[String] {
  override def get(): String => String = identity
}
