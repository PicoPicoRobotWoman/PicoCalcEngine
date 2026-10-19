package pico.calc.framework.core.dispatcher

import pico.calc.framework.api.{Contract, FunctionSpec, Name, Default}

object FunctionDispatcher {

  def apply(specs: Set[FunctionSpec[?]]): (String, List[String]) => Option[FunctionSpec[?]] = {
    val specMap: Map[String, List[FunctionSpec[?]]] =
      specs.toList.groupBy(sp => DispatcherHelper.getName(sp))

    (name, argTypes) => {
      specMap.get(name)
        .flatMap {
          functions =>
            functions
              .find(sp =>
                DispatcherHelper.getArgTypes(sp)
                  .equals(argTypes)
              )
              .orElse(
                functions.find(_.getClass.isAnnotationPresent(classOf[Default]))
              )
        }
    }
  }

}
