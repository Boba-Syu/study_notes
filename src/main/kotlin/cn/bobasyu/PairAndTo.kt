package cn.bobasyu

/**
 * kotlin中缀符号调用
 * kotlin中中缀符号to调用会返回Pair对象
 */
fun main() {
    val a: Pair<String, Int> = "one" to 1
    println(a.first)
    println(a.second)
}