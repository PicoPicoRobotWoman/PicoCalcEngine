package pico.calc.framework.core

import pico.calc.framework.compiler.ScalaCompiler

object Compiler {
  def apply[T](code: String): T = {

    val clazz = ScalaCompiler.compile(
      source = code,
      className = "pico.calc.framework.generated.Formula"
    )

    clazz
      .getDeclaredConstructor()
      .newInstance()
      .asInstanceOf[T]
  }

}
