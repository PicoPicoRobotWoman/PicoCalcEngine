package pico.calc.framework.literal

import pico.calc.framework.api.{LiteralSpec, Name}

@Name("Double")
final class DoubleLiteral extends LiteralSpec {
  override def get(): String => Any = _.toDouble
}
