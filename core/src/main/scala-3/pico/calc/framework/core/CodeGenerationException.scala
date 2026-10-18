package pico.calc.framework.core

sealed abstract class CodeGenerationException(message: String, cause: Throwable = null)
    extends RuntimeException(message, cause)

final class UnsupportedAstNodeException(node: AstNode)
    extends CodeGenerationException(s"Unsupported AST node: ${node.getClass.getName}")

final class CodeGenerationFailedException(node: AstNode, cause: Throwable)
    extends CodeGenerationException(
      s"Failed to generate code for AST node: ${node.getClass.getName}",
      cause
    )
