package pico.calc.framework.api

@LiteralMeta(name = "Double")
final class DoubleLiteral extends LiteralSpec {
  override def get(): String => Any = _.toDouble
}
