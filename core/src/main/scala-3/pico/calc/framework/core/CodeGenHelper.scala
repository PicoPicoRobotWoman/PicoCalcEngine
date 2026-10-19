package pico.calc.framework.core

import pico.calc.framework.api.{ConstantSpec, Contract, FunctionSpec, LiteralSpec}
import pico.calc.framework.core.configuracion.Config
import pico.calc.framework.core.dispatcher.DispatcherHelper

object CodeGenHelper {

  final case class Helpers(
    resolveLiteral: String => LiteralSpec[?],
    resolveFunction: (String, List[String]) => FunctionSpec[?],
    resolveConstant: String => ConstantSpec[?],
    getImports: AstNode => Set[Class[?]],
    getAllLiteralSpecs: AstNode => Set[LiteralSpec[?]],
    getAllFunctionSpecs: AstNode => Set[FunctionSpec[?]],
    getAllConstantSpecs: AstNode => Set[ConstantSpec[?]],
    getReturnType: AstNode => String
  )

  def getContract(obj: Any): Contract =
    obj.getClass.getAnnotation(classOf[Contract])

  def getAllParamNodes(ast: AstNode): List[ParamNode] =
    ast match
      case FunctionNode(_, args) =>
        args.iterator
          .flatMap(getAllParamNodes)
          .toList
      case n: ParamNode =>
        List(n)
      case _ =>
        List.empty

  def getAllOrderParam(ast: AstNode): List[ParamNode] =
    getAllParamNodes(ast).sortBy(_.ordered)

  def apply(
    literalDispatcher: String => Option[LiteralSpec[?]],
    functionDispatcher: (String, List[String]) => Option[FunctionSpec[?]],
    constantDispatcher: String => Option[ConstantSpec[?]]
  )(using config: Config = Config()): Helpers =

    def getReturnType(ast: AstNode): String =
      (ast, config) match {
        case (param: ParamNode, Config(true, _, _, _)) => param.returnType
        case (ParamNode, Config(false, _, _, _))           => "Any"
        case (literal: LiteralNode, _) => getContract(resolveLiteral(literal.name)).returnType()
        case (constant: ConstantNode, _) => getContract(resolveConstant(constant.name)).returnType()
        case (function: FunctionNode, _) => {
          val argTypes: List[String] = function.ijos.map(getReturnType)
          DispatcherHelper.getContract(resolveFunction(function.name, argTypes)).returnType()
        }
        case (_, _)                                        => "Any"
      }

    def resolveLiteral(name: String): LiteralSpec[?] =
      literalDispatcher(name).getOrElse(
        throw new NoSuchElementException(s"Unknown literal: $name")
      )

    def resolveFunction(name: String, argTypes: List[String]): FunctionSpec[?] =
      functionDispatcher(name, argTypes).getOrElse(
        throw new NoSuchElementException(s"Unknown Function: $name")
      )

    def resolveConstant(name: String): ConstantSpec[?] =
      constantDispatcher(name).getOrElse(
        throw new NoSuchElementException(s"Unknown Constant: $name")
      )

    def getImports(ast: AstNode): Set[Class[?]] =
      ast match
        case LiteralNode(name, _) =>
          Set(resolveLiteral(name).getClass)
        case FunctionNode(name, args) =>
          val argTypes: List[String] = args.map(getReturnType)
          args.iterator
            .flatMap(getImports)
            .toSet + resolveFunction(name, argTypes).getClass
        case ConstantNode(name) =>
          Set(resolveConstant(name).getClass)
        case _: ParamNode => Set.empty

    def getAllLiteralSpecs(ast: AstNode): Set[LiteralSpec[?]] =
      ast match
        case LiteralNode(name, _) =>
          Set(resolveLiteral(name))
        case FunctionNode(_, args) =>
          args.iterator
            .flatMap(getAllLiteralSpecs)
            .toSet
        case _ => Set.empty

    def getAllFunctionSpecs(ast: AstNode): Set[FunctionSpec[?]] =
      ast match
        case FunctionNode(name, args) =>
          val argTypes: List[String] = args.map(getReturnType)
          args.iterator
            .flatMap(getAllFunctionSpecs)
            .toSet + resolveFunction(name, argTypes)
        case _ => Set.empty

    def getAllConstantSpecs(ast: AstNode): Set[ConstantSpec[?]] =
      ast match
        case ConstantNode(name)    => Set(resolveConstant(name))
        case FunctionNode(_, args) =>
          args.iterator
            .flatMap(getAllConstantSpecs)
            .toSet
        case _ => Set.empty

    Helpers(
      resolveLiteral = resolveLiteral,
      resolveFunction = resolveFunction,
      resolveConstant = resolveConstant,
      getImports = getImports,
      getAllLiteralSpecs = getAllLiteralSpecs,
      getAllFunctionSpecs = getAllFunctionSpecs,
      getAllConstantSpecs = getAllConstantSpecs,
      getReturnType = getReturnType
    )
}
