package pico.calc.framework.core.dispatcher

import pico.calc.framework.api.{Name, LiteralSpec}

object LiteralDispatcher {

  def apply(specs: Set[LiteralSpec]): String => Option[LiteralSpec] = {
    val specMap: Map[String, LiteralSpec] = specs
      .map { spec =>
        val meta = spec.getClass.getAnnotation(classOf[Name])
        meta.value() -> spec
      }
      .toMap

    name => specMap.get(name)
  }
}
