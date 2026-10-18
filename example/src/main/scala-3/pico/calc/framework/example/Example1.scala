package pico.calc.framework.example

import pico.calc.framework.api.{ConstantSpec, FunctionSpec, LiteralSpec}
import pico.calc.framework.constant.{EulerConstant, PiConstant}
import pico.calc.framework.core.{AstNode, ConstantNode, ExpressionCompiler, FunctionNode, LiteralNode, ParamNode}
import pico.calc.framework.function.SumarFuncion
import pico.calc.framework.literal.{BooleanLiteral, IntLiteral, TextLiteral}

object Example1 extends App {

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

  val compile = ExpressionCompiler(literals, functions, constants)

  val ast1 = LiteralNode("Text", "спасибо")
  val ast2 = LiteralNode("Int", "1")
  val ast3 = LiteralNode("Boolean", "true")
  val ast4 = ConstantNode("E")

  val sumAst = FunctionNode(
    "+",
    List(
      LiteralNode("Int", "1"),
      ParamNode("a")
    )
  )

  println(compile(ast1).apply(Map.empty))
  println(compile(ast2).apply(Map.empty))
  println(compile(ast3).apply(Map.empty))
  println(compile(ast4).apply(Map.empty))

  println(compile(sumAst).apply(Map("a" -> 2)))

}
