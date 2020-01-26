package cn.bobasyu.`class`

/**
 * kotlin的class中属性的getter和setter方法
 */
class GetterAndSetter {
    // 设置私有set方法不可访问
    var a: Int = 0
        private set
    // 重写getter和setter方法
    var b: String = ""
        set(value) {
            field = value.toLowerCase()
        }
        get() = field.toLowerCase()
    // 属性没有支持字段, 每次访问时都通过计算得到
    val c: String
        get() = b.toUpperCase()
}
