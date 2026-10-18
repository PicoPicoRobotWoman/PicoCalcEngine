package pico.calc.framework.core.dispatcher

import pico.calc.framework.api.{FunctionMeta, FunctionSpec}

object FunctionDispatcher {

  def apply(specs: Set[FunctionSpec]): String => Option[FunctionSpec] = {
    val specMap: Map[String, FunctionSpec] = specs
      .map { spec =>
        val meta = spec.getClass.getAnnotation(classOf[FunctionMeta])
        meta.name -> spec
      }
      .toMap

    name => specMap.get(name)
  }

}
