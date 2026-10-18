package pico.calc.framework.literal

import pico.calc.framework.api.{LiteralSpec, Name}

@Name("Long")
final class LongLiteral extends LiteralSpec {
  override def get(): String => Any = _.toLong
}
