package cn.bobasyu.scala

object Hello {
  def main(args: Array[String]): Unit = {
    val helloWorld: String = "Hello, world!"
    println(helloWorld)
    val a = new A(2)

  }

  class A(val x: Int) {
    val unary_+ = new A(x)
    val unary_- = new A(-x)

    def apply(): A = {
      println("A is created.")
      new A(x)
    }
  }

}
