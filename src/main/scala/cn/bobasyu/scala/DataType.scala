package cn.bobasyu.scala

import scala.Array.{concat, ofDim}

/**
 * Scala变量类型
 *
 * var 为可变变量
 * val 为不可变类型
 */
object DataType {
  // 基本类型
  val byte: Byte = 1
  val short: Short = 2
  val int: Int = 3
  val long: Long = 4L
  val float: Float = 5.0f
  val double: Double = 6.0
  val boolean: Boolean = true
  val char: Char = 'a'

  // 字符串
  val string: String = "Hello world!"
  val mulString: String =
    """Hello
      |World.""".stripMargin
  // 同时给多个变量赋值
  val (a: Int, b: Int) = (2, 2)


  // 数组
  val arr: Array[Int] = new Array[Int](5)
  for (i <- arr.indices) {
    arr(i) = i
  }
  val arr2: Array[Int] = Array(1, 2, 3)
  //合并字符串
  val arr3: Array[Int] = concat(arr, arr2)
  // 二维函数
  var matrix: Array[Array[Int]] = ofDim[Int](3, 3)
  for (i <- matrix.indices) {
    for (j <- matrix(i).indices) {
      matrix(i)(j) = i * j
    }
  }

  def main(args: Array[String]): Unit = {

  }
}
