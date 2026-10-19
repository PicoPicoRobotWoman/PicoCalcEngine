package pico.calc.framework.core.configuracion

import pico.calc.framework.core.configuracion.FunctionSignature.MapBased

case class Config(
  parameterizeArguments: Boolean = true,
  parameterizeReturnType: Boolean = true,
  signature: FunctionSignature = MapBased,
  showCode: Boolean = false
)
