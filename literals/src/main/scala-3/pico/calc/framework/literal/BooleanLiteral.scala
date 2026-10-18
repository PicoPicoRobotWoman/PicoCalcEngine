package pico.calc.framework.literal

import pico.calc.framework.api.{LiteralSpec, Name}

@Name("Boolean")
final class BooleanLiteral extends LiteralSpec {
  override def get(): String => Any = _.toBoolean
}
