package pico.calc.framework.core

import pico.calc.framework.api.{ConstantSpec, FunctionSpec, LiteralSpec}
import pico.calc.framework.core.configuracion.Config
import pico.calc.framework.core.dispatcher.{ConstantDispatcher, FunctionDispatcher, LiteralDispatcher}

object ExpressionCompiler {
    def apply[T](
                            literals: Set[LiteralSpec],
                            functions: Set[FunctionSpec],
                            constants: Set[ConstantSpec]
                          )(ast: AstNode)(using config: Config = Config()): T = {
    val literalDispatcher = LiteralDispatcher(literals)
    val functionDispatcher = FunctionDispatcher(functions)
    val constantDispatcher = ConstantDispatcher(constants)
    val helpers = CodeGenHelper(
      literalDispatcher,
      functionDispatcher,
      constantDispatcher
    )

    val source = ScalaCodeGenerator(helpers, config)(ast)
    println(source)  
    Compiler[T](source)
  }
}
