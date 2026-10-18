package pico.calc.framework.literal

import pico.calc.framework.api.{LiteralMeta, LiteralSpec}

@LiteralMeta(name = "Double")
final class DoubleLiteral extends LiteralSpec {
  override def get(): String => Any = _.toDouble
}
