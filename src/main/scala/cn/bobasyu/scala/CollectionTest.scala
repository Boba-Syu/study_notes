package cn.bobasyu.scala

/**
 * Scala集合
 */
object CollectionTest {
  def listTest(): Unit = {
    // 定义列表
    val str1: List[String] = List("aaa", "bbb", "ccc")
    // 连接列表, 或者使用concat()函数
    val str2: List[String] = str1 ::: List("ddd", "eee", "fff")
    val nums: List[Int] = 1 :: (2 :: (3 :: (4 :: Nil)))
    println("-----------")
    // 获取元素
    println(str1.apply(1))
    // 是否包含
    println(str1.contains("aaa"))
    // 添加元素
    println("sss" +: str1)
    // 删除重复元素
    println(str1.distinct)
    // 删除元素
    println(str1.drop(1))
    // 翻转列表
    println(str1.reverse)
    // 制定重复元素1三次
    println(List.fill(3)(1))
    println("----------")

    // 遍历列表
    var n = str2
    while (n.nonEmpty) {
      println(n.head)
      n = n.tail
    }
    println("-----------")
    for (i <- nums) {
      println(i)
    }
    println("-----------")
    str1.foreach(i => println(i))
  }

  def setTest(): Unit = {
    // 定义Set
    val set1: Set[Int] = Set(1, 2, 3)
    // 连接Set
    val set2: Set[Int] = set1 ++ Set(4, 5)

    // 最大值
    println(set1.max)
    // 最小值
    println(set1.min)
    // 交集, 或者使用intersect()方法
    println(set1.&(set2))
    // 添加元素
    println(set1 + 19)

    println("-----------")
    // 遍历
    set2.foreach(i => println(i))
  }

  def mapTest(): Unit = {
    // 定义Map
    var map: Map[String, String] = Map()
    var map2: Map[String, String] = Map()
    // 添加元素
    map += ("AAA" -> "aaa")
    map += ("BBB" -> "bbb")
    map2 += ("CCC" -> "ccc")
    // 合并Map
    println(map ++ map2)
    // 是否包含键
    println(map.contains("AAA"))
    println("-------------")
    // 遍历Map
    map.keys.foreach(k => println(s"key: $k, value: ${map(k)}"))
  }

  def tupleTest(): Unit = {
    // 定义元祖
    val tuple1 = (1, "2")
    val tuple2 = Tuple2("a", "b")
    val tuple3 = Tuple3(1, 2, 3)
    // 访问元素
    println(tuple1._1)
    println(tuple2.swap)

    println("-------------")
    // 遍历元素
    tuple3.productIterator.foreach(i => println(i))
  }

  def main(args: Array[String]): Unit = {
    tupleTest()
  }
}
