package pico.calc.framework.core

import pico.calc.framework.api.{ConstantSpec, FunctionSpec, LiteralSpec}
import pico.calc.framework.core.dispatcher.{ConstantDispatcher, FunctionDispatcher, LiteralDispatcher}

object ExpressionCompiler {
  
  def apply(literals: Set[LiteralSpec], functions: Set[FunctionSpec], constants: Set[ConstantSpec])(ast: AstNode): Map[String, Any] => Any = {

    val literalDispatcher = LiteralDispatcher(literals)
    val functionDispatcher = FunctionDispatcher(functions)
    val constantDispatcher = ConstantDispatcher(constants)
    val helpers = CodeGenHelper(literalDispatcher, functionDispatcher, constantDispatcher)

    val source = ScalaCodeGenerator(helpers)(ast)

    Compiler(source)
    
  }

}
