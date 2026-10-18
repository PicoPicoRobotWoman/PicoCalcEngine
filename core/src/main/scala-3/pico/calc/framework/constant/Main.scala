package pico.calc.framework.constant

import pico.calc.framework.api.{BooleanLiteral, ConstantSpec, FunctionSpec, IntLiteral, LiteralSpec, TextLiteral}
import pico.calc.framework.constant.dispatcher.{ConstantDispatcher, FunctionDispatcher, LiteralDispatcher}

object Main extends App {

  val literals: Set[LiteralSpec] = Set(
    IntLiteral(),
    BooleanLiteral(),
    TextLiteral()
  )

  val functions: Set[FunctionSpec] = Set(
    SumarFuncion()
  )

  val constans: Set[ConstantSpec] = Set(
    EulerConstant(),
    PiConstant()
  )

  val literlDispatcher = LiteralDispatcher(literals)
  val functionDispatcher = FunctionDispatcher(functions)
  val constantDispatcher = ConstantDispatcher(constans)
  val helpers = CodeGenHelper(literlDispatcher, functionDispatcher, constantDispatcher)

  def compile(ast: AstNode): Map[String, Any] => Any =
    val source = ScalaCodeGenerator(helpers)(ast)
    ScalaCompiler(source)

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
