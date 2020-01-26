package cn.bobasyu

import java.lang.Exception

/**
 * kotlin 捕获异常
 * kotlin的try-catch带有返回值
 */
fun main() {
    val a = try {
        1 / 2
    } catch (e: Exception) {
        -1
    }
    println(a)
}
