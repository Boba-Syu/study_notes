package cn.bobasyu.`class`

// 定义接口
interface Interface1 {
    fun test1()
    fun test2() = println("默认方法")
}

interface Interface2

// 实现接口
class Class1 : Interface1 {
    override fun test1() {
        println("实现接口方法")
    }

}

// 抽象类, 同时实现多个接口
abstract class Class2 : Interface1, Interface2

// 继承类, 只有被open或sealed或abstract修饰的类才能被继承
class Class3 : Class2() {
    override fun test1() {
        println("实现抽象类方法")
    }
}


fun main() {
    val a = Class1()
    a.test1()
    a.test2()
}
