package pico.calc.framework.example

import pico.calc.framework.api.{ConstantSpec, FunctionSpec, LiteralSpec}
import pico.calc.framework.constant.{EulerConstant, PiConstant}
import pico.calc.framework.core.configuracion.Config
import pico.calc.framework.core.configuracion.FunctionSignature.Positional
import pico.calc.framework.core.{ExpressionCompiler, FunctionNode, LiteralNode, ParamNode}
import pico.calc.framework.function.SumarFuncion
import pico.calc.framework.literal.{BooleanLiteral, IntLiteral, TextLiteral}

object Example2 extends App {

  implicit val config: Config = Config(Positional)

  val literals: Set[LiteralSpec] = Set(
    IntLiteral(),
    BooleanLiteral(),
    TextLiteral()
  )

  val functions: Set[FunctionSpec] = Set(
    SumarFuncion()
  )

  val constants: Set[ConstantSpec] = Set(
    EulerConstant(),
    PiConstant()
  )

  val compile1 = ExpressionCompiler[Int => Any](literals, functions, constants)
  val compile2 = ExpressionCompiler[(Int, Int) => Any](literals, functions, constants)
  val compile3 = ExpressionCompiler[() => Any](literals, functions, constants)

  val ast1 = FunctionNode("+", List(LiteralNode("Int", "1"), ParamNode("a", 1)))
  val ast2 = FunctionNode("+", List(ParamNode("a", 1), ParamNode("b", 2)))
  val ast3 = FunctionNode("+", List(LiteralNode("Int", "4"), LiteralNode("Int", "6")))

  println(compile1(ast1).apply(1))
  println(compile2(ast2).apply(2, 2))
  println(compile3(ast3).apply())

}
