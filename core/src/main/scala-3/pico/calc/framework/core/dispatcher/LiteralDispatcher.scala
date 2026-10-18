package pico.calc.framework.core.dispatcher

import pico.calc.framework.api.{LiteralMeta, LiteralSpec}

object LiteralDispatcher {

  def apply(specs: Set[LiteralSpec]): String => Option[LiteralSpec] = {
    val specMap: Map[String, LiteralSpec] = specs
      .map { spec =>
        val meta = spec.getClass.getAnnotation(classOf[LiteralMeta])
        meta.name -> spec
      }
      .toMap

    name => specMap.get(name)
  }
}
