package pico.calc.framework.core

import pico.calc.framework.core.configuracion.Config
import pico.calc.framework.core.configuracion.FunctionSignature.{MapBased, Positional}

object ScalaCodeGenerator:

  def apply(
    helpers: CodeGenHelper.Helpers,
    config: Config
  ): AstNode => String =

    def generateExpression(ast: AstNode): String =
      (ast, config) match
        case (LiteralNode(name, value), _) =>
          val specValName = toValName(helpers.resolveLiteral(name).getClass.getSimpleName)
          s"""$specValName.apply("$value")"""
        case (FunctionNode(name, args), _) =>
          val specValName = toValName(helpers.resolveFunction(name).getClass.getSimpleName)
          val generatedArgs =
            args.map(generateExpression).mkString(", ")
          s"$specValName.apply(List[Any]($generatedArgs))"
        case (ConstantNode(name), _) =>
          val specValName = toValName(helpers.resolveConstant(name).getClass.getSimpleName)
          s"$specValName.apply()"
        case (ParamNode(name, _), Config(MapBased)) =>
          f"params(\"${name}\")"
        case (ParamNode(name, _), Config(Positional)) =>
          name

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

      val signature: String = config.signature match 
        case Positional => f"(${CodeGenHelper.getAllParamNodes(ast).indices.map(_ => "Any").mkString(", ")})"
        case MapBased => "Map[String, Any]"

      val applyParameters: String = config.signature match
        case Positional => CodeGenHelper.getAllOrderParam(ast).map(p => s"${p.name}: Any").mkString(", ")
        case MapBased => "params: Map[String, Any]"

      val body = generateExpression(ast)

      s"""package pico.calc.framework.generated

         |$imports
         |
         |class Formula extends ($signature => Any):
         |  $literalVals
         |  $functionVals
         |  $constantVals
         |
         |  override def apply($applyParameters): Any =
         |    $body
         |""".stripMargin

  private def toValName(className: String): String =
    className.headOption match
      case Some(head) => head.toLower.toString + className.tail
      case None       => className
