package pico.calc.framework.constant

import pico.calc.framework.api.{ConstantSpec, Name, Contract}

@Name("E")
@Contract(returnType = "Double")
class EulerConstant extends ConstantSpec[Double] {
  def get(): () => Double = () => Math.E
}
