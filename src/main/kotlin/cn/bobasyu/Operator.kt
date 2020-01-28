package cn.bobasyu

/**
 * kotlin 重载运算符
 */
data class Operator(var x: Int, var y: Int) {
    /**
     * 通过编写plus()方法, 重写 + 运算符
     * 方法钱需要加上operator关键字
     */
    operator fun plus(other: Operator): Operator {
        return Operator(this.x + other.x, this.y + other.y)
    }

    /**
     * 重写 - 运算符
     */
    operator fun minus(other: Operator): Operator {
        return Operator(this.x - other.x, this.y - other.y)
    }

    /**
     * 重写 * 运算符
     */
    operator fun times(other: Operator): Operator {
        return Operator(this.x * other.x, this.y * other.y)
    }

    /**
     * 重写 / 运算符
     */
    operator fun div(other: Operator): Operator {
        return Operator(this.x / other.x, this.y / other.y)
    }

    /**
     * 重写 % 运算符
     */
    operator fun rem(other: Operator): Operator {
        return Operator(this.x % other.x, this.y % other.y)
    }

    /**
     * 重写 -(取反) 运算符
     */
    operator fun unaryMinus(): Operator {
        return Operator(this.x - 1, this.y - 1)
    }

    /**
     * 重写 == 运算符
     */
    override operator fun equals(other: Any?): Boolean {
        if (other === this) {
            return true
        }
        if (other !is Operator)
            return false
        return this.x == other.x && this.y == other.y
    }

    /**
     * 重写比较运算符
     */
    operator fun compareTo(other: Operator): Int {
        if (this.x > other.x) {
            return 1
        } else if (this.x < other.x) {
            return -1
        }
        if (this.y > other.y) {
            return 1
        } else if (this.y < other.y) {
            return -1
        }
        return 0
    }

    /**
     * 重写 [] 运算符
     * 可通过下标访问元素, 例: a[i]
     */
    operator fun get(i: Int) = when (i) {
        0 -> this.x
        1 -> this.y
        else -> throw IndexOutOfBoundsException("Invalid coordinate $i")
    }

    /**
     * 重写 [] 运算符
     * 可通过下标赋值, 例: a[key] = value
     */
    operator fun set(key: Int, value: Int) {
        when (key) {
            0 -> this.x = value
            1 -> this.y = value
            else -> throw IndexOutOfBoundsException("Invalid coordinate $key")
        }

    }

    override fun hashCode(): Int {
        var result = x
        result = 31 * result + y
        return result
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val a = Operator(1, 2)
            val b = Operator(3, 4)
            println(a + b)
            println(a - b)
            println(a * b)
            println(a / b)
            println(a % b)
            println(-a)
            println(a == Operator(1, 2))
            println(a > b)
            println(a == b)
            println(a < b)
            println(a[0])
        }
    }
}
