package cn.bobasyu.scala

/**
 * scala函数
 */
object FunctionTest {
  def main(args: Array[String]): Unit = {
    val functionTest = new FunctionTest

    functionTest.f1()
    println("----------------------------")

    println(functionTest.f2())
    println("----------------------------")

    functionTest.f3("a", "b", "c", "d")
    println("----------------------------")

    println(functionTest.f4((x: Int) => s"str$x", 1))
    println("----------------------------")

    functionTest.f5()
    println("----------------------------")

    functionTest.f6(1, 2)
    println("----------------------------")

    val f7: Int => Int = functionTest.f7(2)
    println(f7(3))
    println("----------------------------")
  }
}

class FunctionTest {
  def f1(): Unit = {
    println("无参数, 无返回值.")
  }

  def f2(a: Int = 1): Int = {
    println("有参数, 有返回值, 带有默认参数.")
    a + 1
  }

  def f3(args: String*): Unit = {
    for (i <- args) {
      println(s"可变参数类型, $i.")
    }
  }

  def f4(f: Int => String, v: Int): String = {
    println(
      """|高阶函数, 函数最为参数传递.
         |该函数讲整型转化为字符串, 转化方式为作为参数穿过来的函数""".stripMargin)
    f(v)
  }

  def f5(): Unit = {
    val msg: String = "嵌套函数, nf5只能在f5内调用."
    nf5()

    def nf5(): Unit = {
      println(msg)
    }
  }

  val f6: (Int, Int) => Int = (x: Int, y: Int) => {
    println("匿名函数, 类型为 '(参数类型) => 返回类型'.")
    x + y
  }

  def f7(x: Int)(y: Int): Int = {
    println(
      """|柯里化函数
         |一个带有多个参数, 并引入到一个的函数, 每个函数都只使用一个参数""".stripMargin)
    x * y
  }
}
