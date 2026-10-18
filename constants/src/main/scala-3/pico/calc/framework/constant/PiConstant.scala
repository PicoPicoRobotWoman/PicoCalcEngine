package pico.calc.framework.constant

import pico.calc.framework.api.{ConstantMeta, ConstantSpec}

@ConstantMeta(name = "PI")
class PiConstant extends ConstantSpec {
  def get(): () => Any = () => Math.PI
}
