package pico.calc.framework.core

object ScalaCodeGenerator:

  def apply(
             helpers: CodeGenHelper.Helpers
           ): AstNode => String =

    def generateExpression(ast: AstNode): String = {
      ast match
        case LiteralNode(name, value) =>
          val specValName = toValName(
            helpers.resolveLiteral(name).getClass.getSimpleName
          )
          s"""$specValName.apply("${value}")"""
        case FunctionNode(name, args) =>
          val specValName = toValName(helpers.resolveFunction(name).getClass.getSimpleName)
          val generatedArgs =
            args.map(generateExpression).mkString(", ")
          s"$specValName.apply(List[Any]($generatedArgs))"
        case ConstantNode(name) =>
          val specValName = toValName(helpers.resolveConstant(name).getClass.getSimpleName)
          s"$specValName.apply()"
        case ParamNode(name) =>
          f"params(\"${name}\")"
        case node =>
          throw new UnsupportedAstNodeException(node)
    }

    ast =>
      val imports =
        helpers.getImports(ast)
          .map(cls => s"import ${cls.getCanonicalName}")
          .mkString("\n")

      val literalVals =
        helpers.getAllLiteralSpecs(ast)
          .map { spec =>
            val className = spec.getClass.getSimpleName
            val valName = toValName(className)
            s"val $valName: String => Any = new $className().get()"
          }
          .mkString("\n")

      val functionVals =
        helpers.getAllFunctionSpecs(ast)
          .map { spec =>
            val className = spec.getClass.getSimpleName
            val valName = toValName(className)
            s"val $valName: List[Any] => Any = new $className().get()"
          }
          .mkString("\n")

      val constantVals = helpers.getAllConstantSpecs(ast)
        .map { spec =>
          val className = spec.getClass.getSimpleName
          val valName = toValName(className)
          s"val $valName: () => Any = new $className().get()"
        }
        .mkString("\n")


      val body = generateExpression(ast)

      s"""package pico.calc.framework.generated

         |$imports
         |
         |class Formula extends (Map[String, Any] => Any):
         |  $literalVals
         |  $functionVals
         |  $constantVals
         |
         |  override def apply(params: Map[String, Any]): Any =
         |    $body
         |""".stripMargin

  private def toValName(className: String): String =
    className.headOption match
      case Some(head) => head.toLower.toString + className.tail
      case None       => className

