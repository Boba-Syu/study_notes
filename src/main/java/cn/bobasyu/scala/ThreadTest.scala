package cn.bobasyu.scala

/**
 * Scala线程学习
 */
object ThreadTest {
  def main(args: Array[String]): Unit = {
    0 to 10 foreach { _ => new ThreadTest().start() }
  }
}

class ThreadTest extends Thread {
  override def run(): Unit = {
    0 to 10 foreach { i => {
      println(s"$getName is running: $i")
      Thread.sleep(2)
    }
    }
  }
}
