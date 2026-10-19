package pico.calc.framework.literal

import pico.calc.framework.api.{LiteralSpec, Name, Contract}

@Name("Boolean")
@Contract(returnType = "Boolean")
final class BooleanLiteral extends LiteralSpec[Boolean] {
  override def get(): String => Boolean = _.toBoolean
}
