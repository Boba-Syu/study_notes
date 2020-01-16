package cn.bobasyu.scala

import java.util.stream.IntStream

/**
 * Scala线程学习
 */
object ThreadTest {
  def main(args: Array[String]): Unit = {
    IntStream.range(0, 10).forEach(_ => new ThreadTest().start())
  }
}

class ThreadTest extends Thread {
  override def run(): Unit = {
    IntStream.range(0, 10).forEach(i => {
      println(s"$getName is running: $i")
      Thread.sleep(2)
    })
  }
}
