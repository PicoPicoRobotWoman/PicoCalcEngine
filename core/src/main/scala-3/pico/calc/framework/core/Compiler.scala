package pico.calc.framework.core

import pico.calc.framework.compiler.ScalaCompiler

object Compiler {

  def apply(code: String): Map[String, Any] => Any = {

    val clazz = ScalaCompiler.compile(
      source = code,
      className = "pico.calc.framework.generated.Formula"
    )

    clazz
      .getDeclaredConstructor()
      .newInstance()
      .asInstanceOf[Map[String, Any] => Any]
  }

}
