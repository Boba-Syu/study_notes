package cn.bobasyu

/**
 * kotlin集合
 * kotlin的集合与Java的一致
 */
fun list() {
    val list = arrayListOf<Int>(3, 2, 1)
    list.add(4)
    list.add(8)
    list.remove(3)
    println(list)
}

fun map() {
    val map = hashMapOf<String, Int>("one" to 1, "two" to 2)
    map["three"] = 3
    map.remove("two")
    println(map)
    for ((key, value) in map) {
        println("$key: $value")
    }
}

fun main() {
    list()
    println("-----------")
    map()
    println("-----------")
}
