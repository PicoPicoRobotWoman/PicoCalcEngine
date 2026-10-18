package pico.calc.framework.api

@LiteralMeta(name = "Long")
final class LongLiteral extends LiteralSpec {
  override def get(): String => Any = _.toLong
}
