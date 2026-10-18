package pico.calc.framework.constant

import pico.calc.framework.api.{ConstantSpec, Name}

@Name("E")
class EulerConstant extends ConstantSpec {
  def get(): () => Any = () => Math.E
}
