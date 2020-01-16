package cn.bobasyu.scala

object MatchTest {
  def main(args: Array[String]): Unit = {
    val alice = new MatchTest("Alice", 1)
    val bob = new MatchTest("Bob", 1)
    val charlie = new MatchTest("Charlie", 32)

    for (person <- List(alice, bob, charlie)) {
      classMatch(person)
    }
  }

  /**
   * 整型模式匹配
   *
   * @param x 对传入的整型参数x进行模式匹配
   * @return
   */
  def intMatch(x: Int): String = x match {
    case 0 => "zero"
    case 1 => "one"
    case 2 => "two"
    case _ => "others"
  }

  /**
   * 拨通类型的模式匹配
   *
   * @param x 对传过来的任意数据类型进行模式匹配
   * @return
   */
  def anyMatch(x: Any): Any = x match {
    case 1 => "one"
    case "two" => 2
    case y: Int => "scala.Int"
    case y: Double => "scala.Double"
    case _ => "many"
  }


  /**
   * 使用样例类的模式匹配
   *
   * @param x 对传过来的样例类对象进行模式匹配
   */
  def classMatch(x: MatchTest): Unit = x match {
    case MatchTest("Bob", 1) => println("Hi, Bob")
    case MatchTest("Alice", 1) => println("你好, Alice")
    case MatchTest(name, age) => println(s"name:$name, age:$age")
  }
}

/**
 * 样例类, 标有case关键字的类乐意用于模式匹配
 *
 * @param _name 测试用参数
 * @param _age  测试用参数
 */
case class MatchTest(_name: String, _age: Int) {
  val name: String = _name
  val age: Int = _age
}