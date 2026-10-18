package pico.calc.framework.api

import scala.collection.mutable

final class InMemoryClassFileManager {

  private val classes = mutable.Map.empty[String, Array[Byte]]

  def put(className: String, bytecode: Array[Byte]): Unit =
    classes.update(className, bytecode)

  def get(className: String): Option[Array[Byte]] =
    classes.get(className)

  def contains(className: String): Boolean =
    classes.contains(className)

  def classNames: Set[String] =
    classes.keySet.toSet

  def allClasses: Map[String, Array[Byte]] =
    classes.toMap

  def clear(): Unit =
    classes.clear()

}
