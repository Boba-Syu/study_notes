package cn.bobasyu.java8.stream;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.IntStream;

/**
 * 筛选质数
 *
 * @author Boba
 */
public class PrimerCollector implements Collector<Integer, Map<Boolean, List<Integer>>, Map<Boolean, List<Integer>>> {
    @Override
    public Supplier<Map<Boolean, List<Integer>>> supplier() {
        return () -> new HashMap<Boolean, List<Integer>>(2) {{
            put(true, new ArrayList<>());
            put(false, new ArrayList<>());
        }};
    }

    @Override
    public BiConsumer<Map<Boolean, List<Integer>>, Integer> accumulator() {
        return (Map<Boolean, List<Integer>> acc, Integer candidate) ->
                acc.get(isPrime(acc.get(true), candidate)).add(candidate);
    }

    /**
     * 该收集器不能并行, 所以只能顺序使用, 所以该方法不会被调用
     *
     * @return 该方法不会被调用, 所以不需要实现
     */
    @Override
    public BinaryOperator<Map<Boolean, List<Integer>>> combiner() {
        return (map1, map2) -> {
            map1.get(true).addAll(map2.get(true));
            map1.get(false).addAll(map2.get(false));
            return map1;
        };
    }

    @Override
    public Function<Map<Boolean, List<Integer>>, Map<Boolean, List<Integer>>> finisher() {
        return Function.identity();
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Collections.unmodifiableSet(EnumSet.of(Characteristics.IDENTITY_FINISH));
    }


    /**
     * 用质数数组来判断该数是否为质数
     *
     * @param primes    质数数组
     * @param candidate 需要判断是否为质数的数
     * @return 若为质数则返回true, 否则返回false
     */
    public static boolean isPrime(List<Integer> primes, int candidate) {
        return primes.stream().noneMatch(i -> candidate % i == 0);
    }

    public static void main(String[] args) {
        long fastest = Long.MAX_VALUE;
        int loopCount = 10;
        for (int i = 0; i < loopCount; i++) {
            long start = System.nanoTime();
            partitionsPrimes();
            long duration = (System.nanoTime() - start) / 1_000_000;
            fastest = Math.min(duration, fastest);
        }
        System.out.println("Faster execution done in" + fastest + "msecs.");
    }

    private static void partitionsPrimes() {
        Map<Boolean, List<Integer>> map = IntStream.rangeClosed(2, 1000000).boxed().collect(new PrimerCollector());
        System.out.println(map.get(true));
        System.out.println(map.get(false));
    }
}
