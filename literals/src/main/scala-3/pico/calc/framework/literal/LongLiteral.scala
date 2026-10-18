package pico.calc.framework.literal

import pico.calc.framework.api.{LiteralMeta, LiteralSpec}

@LiteralMeta(name = "Long")
final class LongLiteral extends LiteralSpec {
  override def get(): String => Any = _.toLong
}
