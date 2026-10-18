package pico.calc.framework.constant

import pico.calc.framework.api.ScalaCompilerImpl

object ScalaCompiler {

  def apply(code: String): (Map[String, Any]) => Any = {
    val compiler = new ScalaCompilerImpl

    val clazz = compiler.compile(
      source = code,
      className = "pico.calc.framework.generated.Formula"
    )

    clazz
      .getDeclaredConstructor()
      .newInstance()
      .asInstanceOf[Map[String, Any] => Any]
  }

}
