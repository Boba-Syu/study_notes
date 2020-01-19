package cn.bobasyu.scala

/**
 * Scala中的trait学习
 * trait(特征)相当于Java中的接口
 */
object TraitTest {
  def main(args: Array[String]): Unit = {
    val traitTest: TraitTest = new TraitTest with myTrait {
      override def fun1(): Unit = println("动态继承trait.")
    }
    traitTest.fun1()
  }

  class TraitTest {
    def fun1(): Unit = ???

  }

  trait myTrait {
    def fun1(): Unit
  }

  class myTraitImpl extends myTrait {
    override def fun1(): Unit = {
      println("trait中的抽象方法.")
    }
  }

}
