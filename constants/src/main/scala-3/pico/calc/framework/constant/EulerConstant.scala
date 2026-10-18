package pico.calc.framework.constant

import pico.calc.framework.api.{ConstantMeta, ConstantSpec}

@ConstantMeta(name = "E")
class EulerConstant extends ConstantSpec {
  def get(): () => Any = () => Math.E
}
