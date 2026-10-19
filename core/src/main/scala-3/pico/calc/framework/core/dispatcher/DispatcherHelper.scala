package pico.calc.framework.core.dispatcher

import pico.calc.framework.api.{Contract, Name}

object DispatcherHelper {
  
  def getName(obg: Any): String = {
    obg.getClass.getAnnotation(classOf[Name]).value()
  }
  
  def getArgTypes(obj: Any): List[String] = {
    val contract: Contract = getContract(obj)
    getArgTypes(contract)
  }

  def getArgTypes(contract: Contract): List[String] = {
    contract.argumentTypes().toList
  }

  def getContract(obj: Any): Contract = 
    obj.getClass.getAnnotation(classOf[Contract])

}
