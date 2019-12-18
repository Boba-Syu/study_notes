package cn.bobasyu.java8.lambda;

import lombok.var;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * 学习使用 java 自带的函数式接口
 *
 * @author Boba
 */
public class FunctionalInterfaceLearning {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>(Arrays.asList("aaa", "bbb", "ccc", "ddd", "AAA", "BBB", "CCC", "DDD"));

        // Predicate使用
        var result = predicateFilter(list, (String str) -> "AAA".equals(str.toUpperCase()));

        // Consumer使用
        consumerFilter(list, (String str) -> {
            if (str.toUpperCase().equals(str)) {
                System.out.println(str);
            }
        });

        // Function使用
        List<Integer> list1 = functionFilter(list, String::length);
    }

    /**
     * 接口 Predicate 中含有test(T t)方法
     * test(T t)返回值为Boolean型, 可用于删选过滤
     *
     * @param list
     * @param p
     * @param <T>
     * @return
     */
    public static <T> List<T> predicateFilter(List<T> list, Predicate<T> p) {
        List<T> result = new ArrayList<>();
        for (T t : list) {
            if (p.test(t)) {
                result.add(t);
            }
        }
        return result;
    }

    /**
     * 接口 Consumer 中含有accept(T t)方法
     * accept(T t)无返回值
     *
     * @param list
     * @param c
     * @param <T>
     */
    public static <T> void consumerFilter(List<T> list, Consumer<T> c) {
        for (T t : list) {
            c.accept(t);
        }
    }

    /**
     * 接口 Function<T, R> 中含有apply(T t)方法
     * apply(T t)参数和返回值不一样
     *
     * @param list
     * @param f
     * @param <T>
     * @param <R>
     * @return
     */
    public static <T, R> List<R> functionFilter(List<T> list, Function<T, R> f) {
        List<R> result = new ArrayList<>();
        for (T t : list) {
            result.add(f.apply(t));
        }
        return result;
    }
}
