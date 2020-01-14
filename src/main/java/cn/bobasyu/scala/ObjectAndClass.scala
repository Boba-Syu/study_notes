package cn.bobasyu.scala

/**
 * Scala类和变量
 *
 * object 为单例对象, 不能实例化
 * class 为普通的类, 能够被实例化
 */
object ObjectAndClass {
  def main(args: Array[String]): Unit = {
    val classTest = new ObjectAndClass(1, 2)
    println(classTest.aPlusB())
    val test: Test = new Test(10, 20)
    test.printAll()
  }


  class ObjectAndClass(_a: Int, _b: Int) {
    var a: Int = _a
    val b: Int = _b

    def aPlusB(): Int = a + b
  }

  // 继承
  class Test(val _a: Int, val _b: Int) extends ObjectAndClass(_a, _b) {
    def printAll(): Unit = {
      for (i <- 0 until 3) {
        println(s"a = $a, b = $b")
      }
    }

    class InnerClass {
      // 能被scala包下的类访问
      private[scala] var x = null
      // 只能被自己访问
      private[this] var y = null

    }

  }

}

