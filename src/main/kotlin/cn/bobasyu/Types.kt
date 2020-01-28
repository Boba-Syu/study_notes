package cn.bobasyu

/**
 * kotlin的类型系统
 * 能够有好的处理空变量类型
 */
fun main() {
    // ? 可空变量, 变量值可以为空
    val a: String? = null
    // ?. 若变量为空, 则调用方法返回空, 不会报控制正异常
    println(a?.length)
    // ?: 若左边为空, 则返回右边的值
    println(a?.length ?: -1)
    // as? 是这个类型的变量, 则进行类型转换, 否则返回空
    a as? String
    // !! 若为空则报空指针异常
    /** val b = a!! */
    // let 若不为空则执行lambda
    a?.let { println(it.length) }
}

class Types {
    // lateinit 延迟初始化, 若在初始化前访问, 则报出异常
    lateinit var a: String
}
