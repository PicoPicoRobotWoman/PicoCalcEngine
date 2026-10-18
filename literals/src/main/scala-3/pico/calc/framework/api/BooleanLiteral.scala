package pico.calc.framework.api

@LiteralMeta(name = "Boolean")
final class BooleanLiteral extends LiteralSpec {
  override def get(): String => Any = _.toBoolean
}
