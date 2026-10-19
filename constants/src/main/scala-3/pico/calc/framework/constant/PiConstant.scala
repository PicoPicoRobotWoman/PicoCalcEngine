package pico.calc.framework.constant

import pico.calc.framework.api.{ConstantSpec, Name, Contract}

@Name("PI")
@Contract(returnType = "Double")
class PiConstant extends ConstantSpec[Double] {
  def get(): () => Double = () => Math.PI
}
