package cn.bobasyu.java8.lambda;


import java.util.concurrent.Callable;

/**
 * 可以使用lambda表达式的接口称为函数式接口
 * 函数式接口只定义了一个抽象方法且必须有一个抽象方法(包括继承而来的)
 *
 * @author Boba
 */
public class LambdaTest {
    public static void main(String[] args) {
    }


    /**
     * 在Java8中可以通过传递lambda表达式, 也就是匿名函数来代替匿名类
     */
    public void test() {
        Runnable tr = new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello World!");
            }
        };
        // 单行lambda不需要花括号
        Runnable r2 = () -> System.out.println("Hello World!");
        // 多行行lambda需要花括号
        Runnable r3 = () -> {
            System.out.print("Hello ");
            System.out.println("World!");
        };

        Callable<String> c1 = new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "Hello World";
            }
        };
        // 有返回值且不带花括号的lambda表达式不需要return
        Callable<String> c2 = () -> "Hello World";
        // 有返回值且带花括号的lambda表达式需要return
        Callable c3 = () -> {
            return "Hello World!";
        };

        // 所有已函数式接口为参数的地方都可以用lambda表达式代替, 但要保持函数描述符一致(形参和返回值一致)
        test(() -> System.out.println("Hello World"));
    }

    public void test(FunInterface funInterface) {
        funInterface.test();
    }

    /**
     * 注解 @FunctionalInterface 标注在函数式接口上表明这是一个函数式接口
     * 该注解非必须, 但是标记上该注解的接口若不是函数式接口则会报错
     */
    @FunctionalInterface
    private interface FunInterface {
        /**
         * 测试用
         */
        void test();
    }
}
