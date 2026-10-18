package pico.calc.framework.core

import pico.calc.framework.api.{ConstantSpec, FunctionSpec, LiteralSpec}

object CodeGenHelper:

  final case class Helpers(
    resolveLiteral: String => LiteralSpec,
    resolveFunction: String => FunctionSpec,
    resolveConstant: String => ConstantSpec,
    getImports: AstNode => Set[Class[?]],
    getAllLiteralSpecs: AstNode => Set[LiteralSpec],
    getAllFunctionSpecs: AstNode => Set[FunctionSpec],
    getAllConstantSpecs: AstNode => Set[ConstantSpec]
  )

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
    literalDispatcher: String => Option[LiteralSpec],
    functionDispatcher: String => Option[FunctionSpec],
    constantDispatcher: String => Option[ConstantSpec]
  ): Helpers =

    def resolveLiteral(name: String): LiteralSpec =
      literalDispatcher(name).getOrElse(
        throw new NoSuchElementException(s"Unknown literal: $name")
      )
    def resolveFunction(name: String): FunctionSpec =
      functionDispatcher(name).getOrElse(
        throw new NoSuchElementException(s"Unknown Function: $name")
      )
    def resolveConstant(name: String): ConstantSpec =
      constantDispatcher(name).getOrElse(
        throw new NoSuchElementException(s"Unknown Constant: $name")
      )

    def getImports(ast: AstNode): Set[Class[?]] =
      ast match
        case LiteralNode(name, _) =>
          Set(resolveLiteral(name).getClass)
        case FunctionNode(name, args) =>
          args.iterator
            .flatMap(getImports)
            .toSet + resolveFunction(name).getClass
        case ConstantNode(name) =>
          Set(resolveConstant(name).getClass)
        case ParamNode(_, _) => Set.empty

    def getAllLiteralSpecs(ast: AstNode): Set[LiteralSpec] =
      ast match
        case LiteralNode(name, _) =>
          Set(resolveLiteral(name))
        case FunctionNode(_, args) =>
          args.iterator
            .flatMap(getAllLiteralSpecs)
            .toSet
        case _ => Set.empty

    def getAllFunctionSpecs(ast: AstNode): Set[FunctionSpec] =
      ast match
        case FunctionNode(name, args) =>
          args.iterator
            .flatMap(getAllFunctionSpecs)
            .toSet + resolveFunction(name)
        case _ =>
          Set.empty

    def getAllConstantSpecs(ast: AstNode): Set[ConstantSpec] =
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
      getAllConstantSpecs = getAllConstantSpecs
    )
