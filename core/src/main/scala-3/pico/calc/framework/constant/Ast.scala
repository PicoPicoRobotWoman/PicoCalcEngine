package pico.calc.framework.constant

sealed trait AstNode

final case class LiteralNode(name: String, value: String) extends AstNode
final case class FunctionNode(name: String, ijos: List[AstNode]) extends AstNode
final case class ParamNode(name: String) extends AstNode
final case class ConstantNode(name: String) extends AstNode
