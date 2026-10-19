package pico.calc.framework.literal

import pico.calc.framework.api.{LiteralSpec, Name, Contract}

@Name("null")
@Contract(returnType = "Any")
final class NullLiteral extends LiteralSpec[Any] {
  override def get(): String => Any = _ => null
}
