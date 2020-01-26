package cn.bobasyu.`class`

/**
 * kotlin中的data类
 * 使用data关键字的类或重写其toString, equals和hashCode方法
 */
data class Data(val id: Int, val name: String) {
    constructor() : this(1, "abc")
}

fun main() {
    val a = Data()
    println(a)
}