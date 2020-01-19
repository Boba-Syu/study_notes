package cn.bobasyu.scala

/**
 * Scala提取器学习
 * 提取器 是从传递给它的对象中提取出构造该对象的参数
 * 提取器可以根据某一规则，非常方便的获取到想要的值
 */
object ExtractorTest {
  def main(args: Array[String]): Unit = {
    println("Apply 方法: " + apply("bob", "gmail.com"))
    println("Unapply 方法: " + unapply("alice@gmail.com"))
    println("Unapply 方法: " + unapply("bob Ali"))
  }

  // 注入方法
  def apply(user: String, domain: String): String = {
    user + "@" + domain
  }

  // 提取方法
  def unapply(str: String): Option[(String, String)] = {
    val parts = str split "@"
    if (parts.length == 2) {
      Some(parts(0), parts(1))
    } else {
      None
    }
  }
}
