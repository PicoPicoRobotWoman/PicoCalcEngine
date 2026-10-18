package pico.calc.framework.constant

import pico.calc.framework.api.{ConstantSpec, Name}

@Name("PI")
class PiConstant extends ConstantSpec {
  def get(): () => Any = () => Math.PI
}
