package cn.bobasyu.scala

/**
 * Scala中的trait学习
 * trait(特征)相当于Java中的接口
 */
object TraitTest {

  trait myTrait {
    def fun1()
  }

  class myTraitImpl extends myTrait {
    override def fun1(): Unit = {
      println("实现trait中的抽象方法")
    }
  }

}

