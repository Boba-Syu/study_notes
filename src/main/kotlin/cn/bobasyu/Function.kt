package cn.bobasyu

/**
 * kotlin函数
 */
object Function {
    fun fun1() {
        println("无返回值函数")
    }

    fun fun2(): String {
        return "有返回值"
    }

    // 私有方法, a有,默认参数
    private fun fun3(b: Int, a: String = "default") {
        println("带参数, a = $a, b = $b")
    }

    // 单行函数, if-else带有返回值
    private fun fun4(a: Int, b: Int) = if (a > b) a else b

    // 给别的类添加方法, 但是扩展方法不能访问私有变量和方法
    fun String.lastChar(): Char = this[this.length - 1]

    @JvmStatic
    fun main(args: Array<String>) {
        fun1()
        println(fun2())
        // 使用默认参数
        fun3(1)
        // 带参数名而不用固定参数顺序
        fun3(a = "test", b = 1)
        println(fun4(2, 3))

        print("kotlin".lastChar())
    }

}
