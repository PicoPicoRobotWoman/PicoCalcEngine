package pico.calc.framework.constant

import pico.calc.framework.api.ScalaCompilerImpl

object Main extends App {

  val source =
    """
      |package pico.calc.framework.generated
      |
      |class GeneratedCalculator extends ((Int, Int) => Int):
      |  override def apply(a: Int, b: Int): Int =
      |    a + b
      |""".stripMargin

  val compiler = new ScalaCompilerImpl

  val clazz = compiler.compile(
    source = source,
    className = "pico.calc.framework.generated.GeneratedCalculator"
  )

  val instance = clazz.getDeclaredConstructor().newInstance()

  val res = instance.asInstanceOf[Function2[Int, Int, Int]]

  println(res(10, 20))

}
