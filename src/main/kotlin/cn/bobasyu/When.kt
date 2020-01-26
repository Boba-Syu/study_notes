package cn.bobasyu

/**
 * kotlin中可以用when代替switch使用
 * 但是when可以不带参数, 同事有返回值
 */
fun whenTest(i: Int): String {
    return when (i) {
        1 -> "a1"
        2 -> "a2"
        3 -> {
            println("a3")
            return "a3"
        }
        else -> "others"
    }
}

/**
 * 不带参数的when
 */
fun whenTest(a: Int, b: Int): Int {
    return when {
        a > b -> 1
        a < b -> -1
        else -> 0
    }
}