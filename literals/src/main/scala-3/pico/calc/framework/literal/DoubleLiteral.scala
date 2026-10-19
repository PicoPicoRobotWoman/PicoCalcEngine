package pico.calc.framework.literal

import pico.calc.framework.api.{LiteralSpec, Name, Contract}

@Name("Double")
@Contract(returnType = "Double")
final class DoubleLiteral extends LiteralSpec[Double] {
  override def get(): String => Double = _.toDouble
}
