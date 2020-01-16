package cn.bobasyu.scala

/**
 * Scala类和变量
 *
 * object 为单例对象, 不能实例化
 * class 为普通的类, 能够被实例化
 */
object ObjectAndClass {
  def main(args: Array[String]): Unit = {
    val classTest = new ObjectAndClass()
    println(classTest.test())
    val test: Test = new Test(10, 20)
    test.printAll()
  }


  class ObjectAndClass(xa: Int, xb: Int) {
    var a: Int = xa
    val b: Int = xb

    // 构造方法重载
    def this() = {
      this(1, 2)
    }

    def test(): Int = a + b
  }

  // 继承
  class Test(val x: Int, val y: Int) extends ObjectAndClass(x, y) {

    override def test(): Int = {
      println("重写了父类的方法")
      a + b
    }

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

  class Maker private() {
    def fun(): Unit = {
      println("私有构造方法类")
    }
  }

  object Maker {
    def builtMaker(): Maker = {
      return new Maker
    }
  }

}

