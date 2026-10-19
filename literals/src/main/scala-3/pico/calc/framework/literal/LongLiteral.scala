package pico.calc.framework.literal

import pico.calc.framework.api.{LiteralSpec, Name, Contract}

@Name("Long")
@Contract(returnType = "Long")
final class LongLiteral extends LiteralSpec[Long] {
  override def get(): String => Long = _.toLong
}
