package pico.calc.framework.literal

import pico.calc.framework.api.{LiteralSpec, Name}

@Name("null")
final class NullLiteral extends LiteralSpec {
  override def get(): String => Any = _ => null
}
