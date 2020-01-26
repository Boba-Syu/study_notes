package cn.bobasyu.`class`

/**
 * kotlin中class的构造方法
 */
class Constructor constructor(a: Int) {
    val a: Int

    init {
        println("调用主构造方法时调用init中的代码块")
        this.a = a
    }

    /**
     * 构造方法重载
     * 调用自己的构造方法用this
     * 调用父类的构造方法用super
     */
    constructor() : this(0)
}