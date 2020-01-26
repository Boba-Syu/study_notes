package cn.bobasyu

import cn.bobasyu.`class`.Data
import java.util.stream.Collectors

/**
 * kotlin的lambda表达式
 * kotlin的lambda表达式写在花括号中
 */
fun main() {
    collectTest()
}

fun collectTest() {
    val list: List<Data> = arrayListOf(
            Data(1, "aaa"),
            Data(2, "bbb"),
            Data(3, "ccc"),
            Data(4, "aaa"),
            Data(5, "bbb"),
            Data(6, "ccc")
    )
    // 一下几行代码效果相同
    list.maxBy({ data -> data.id })
    list.maxBy({ it.id })
    list.maxBy() { it.id }
    list.maxBy { it.id }
    list.maxBy(Data::id)

    //集合中的API
    // 筛选
    println(list.filter { it.id < 2 })
    // 重新打包
    println(list.map { it.name })
    // 是否存在符合条件的对象
    println(list.any { it.id == 2 })
    // 是否所有对象都符合条件
    println(list.all { it.id < 1 })
    // 找出第一个符合条件的对象
    println(list.find { it.name == "aaa" })
    // 符合条件的对象的个数
    println(list.count { it.id > 1 })
    // 分组
    println(list.groupBy { it.name })
    // 惰性序列asSequence(), 类似于java中的stream()
    println(list.asSequence().toList())
    list.stream().collect(Collectors.toList())
}

fun samTest(): Runnable {
    return Runnable { println("SAM构造方法实现函数式接口") }
}

// with方法使用
fun withTest(): String {
    val stringBuilder = StringBuilder()
    return with(stringBuilder) {
        for (i in 'A'..'Z') {
            this.append(i)
        }
        this.toString()
    }
}

// apply方法使用
fun applyTest(): String {
    return StringBuilder().apply {
        for (i in 'A'..'Z') {
            this.append(i)
        }
    }.toString()
}
