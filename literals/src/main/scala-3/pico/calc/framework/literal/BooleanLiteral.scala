package pico.calc.framework.literal

import pico.calc.framework.api.{LiteralMeta, LiteralSpec}

@LiteralMeta(name = "Boolean")
final class BooleanLiteral extends LiteralSpec {
  override def get(): String => Any = _.toBoolean
}
