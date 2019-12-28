package cn.bobasyu.java8.stream;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Stream;

/**
 * 学习使用Collector接口
 *
 * @author Boba
 */
public class MyCollector<T> implements Collector<T, List<T>, List<T>> {
    /**
     * 简历新的结果容器
     *
     * @return 新产生的容器类
     */
    @Override
    public Supplier<List<T>> supplier() {
        return ArrayList::new;
    }

    /**
     * 累加器, 将对象添加到容器中
     *
     * @return 执行规约操作的函数
     */
    @Override
    public BiConsumer<List<T>, T> accumulator() {
        return (List::add);
    }

    /**
     * 合并两个结果容器
     *
     * @return 执行两个容器合并的函数
     */
    @Override
    public BinaryOperator<List<T>> combiner() {
        return (list1, list2) -> {
            list1.addAll(list2);
            return list1;
        };
    }

    /**
     * 对结果容器应用的最终转换
     *
     * @return 由于该样例并不需要对容器的类型进行转换, 所以调用该方法返回容器自身
     */
    @Override
    public Function<List<T>, List<T>> finisher() {
        return Function.identity();
    }

    /**
     * 定义收集器的行为
     * UNORDERED: 规约结果不受流中项目的遍历和累计顺序的影响
     * CONCURRENT: 函数accumulator()可以多线程同时调用, 可以并行规约流
     * IDENTITY_FINISH: 表明完成器combiner()返回的是恒等函数, 即函数执行后容器不变, 可以跳过执行
     *
     * @return 一个不可变的Characteristics集合
     */
    @Override
    public Set<Characteristics> characteristics() {
        return Collections.unmodifiableSet(EnumSet.of(Characteristics.IDENTITY_FINISH, Characteristics.CONCURRENT));
    }

    public static void main(String[] args) {
        Stream<Dish> menuStream = StreamTest.getMenu().stream();
        // 使用自定义Collector
        List<Dish> dishes = menuStream.collect(new MyCollector<>());
        dishes.parallelStream().forEach(System.out::println);
        System.out.println("----------------------------------");
        // 不具体实现Collector, 在这里要重新获取流, 因为刚刚遍历过menuStream
        dishes = StreamTest.getMenu().parallelStream().collect(
                // 供应源, 相当于supplier()
                ArrayList::new,
                // 累加器, 相当于accumulator()
                List::add,
                // 组合器, 相当于combiner()
                List::addAll
        );
        dishes.parallelStream().forEach(System.out::println);
    }
}
