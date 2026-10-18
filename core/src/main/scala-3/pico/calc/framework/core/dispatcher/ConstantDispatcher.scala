package pico.calc.framework.core.dispatcher

import pico.calc.framework.api.{ConstantMeta, ConstantSpec}

object ConstantDispatcher {
  def apply(specs: Set[ConstantSpec]): String => Option[ConstantSpec] = {
    val specMap: Map[String, ConstantSpec] = specs
      .map { spec =>
        val meta = spec.getClass.getAnnotation(classOf[ConstantMeta])
        meta.name -> spec
      }
      .toMap

    name => specMap.get(name)
  }
}
