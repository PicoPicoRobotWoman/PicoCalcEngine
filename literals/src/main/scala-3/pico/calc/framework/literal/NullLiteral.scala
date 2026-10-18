package pico.calc.framework.literal

import pico.calc.framework.api.{LiteralMeta, LiteralSpec}

@LiteralMeta(name = "null")
final class NullLiteral extends LiteralSpec {
  override def get(): String => Any = _ => null
}
