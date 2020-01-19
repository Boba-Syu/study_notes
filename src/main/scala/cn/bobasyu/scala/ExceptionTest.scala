package cn.bobasyu.scala

import java.io.{FileNotFoundException, FileReader, IOException}

/**
 * Scala的try-catch-finally异常处理
 */
object ExceptionTest {
  def main(args: Array[String]): Unit = {
    var f: FileReader = null
    try {
      f = new FileReader("input.txt")
    } catch {
      case e: FileNotFoundException => e.printStackTrace()
      case e: IOException => e.printStackTrace()
    } finally {
      f.close()
      println("Exiting finally...")
    }
  }

  @throws(classOf[NumberFormatException])
  def throwsTest(): Unit = {
    "abc".toInt
  }
}
