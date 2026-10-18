package pico.calc.framework.api

@LiteralMeta(name = "Int")
final class IntLiteral extends LiteralSpec {
  override def get(): String => Int = _.toInt
}
