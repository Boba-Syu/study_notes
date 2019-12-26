package cn.bobasyu.java8.stream;

import lombok.var;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author Boba
 */
public class StreamTest {
    public static void main(String[] args) {
        threeHighCaloricDishNames();
    }

    public static List<Dish> getMenu() {
        return new ArrayList<>(Arrays.asList(
                new Dish("pork", false, 800, Dish.Type.MEAT),
                new Dish("beef", false, 700, Dish.Type.MEAT),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("fresh fries", true, 530, Dish.Type.OTHER),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("season fruit", true, 120, Dish.Type.OTHER),
                new Dish("pizza", true, 550, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("salmon", false, 450, Dish.Type.FISH)
        ));
    }

    /**
     * 筛选卡路里大于300的前三个菜的名字
     */
    public static void threeHighCaloricDishNames() {
        List<String> threeHighCaloricDishNamesList = getMenu()
                // 获得流对象
                .stream()
                // 筛选卡路里大于300的Dish对象
                .filter(dish -> dish.getCalories() > 300)
                // 逆向排序
                .sorted(Comparator.comparing(Dish::getCalories).reversed())
                // 获取菜名
                .map(Dish::getName)
                // 限制前三个
                .limit(3)
                // 打包为list
                .collect(Collectors.toList());
        // 流操作遍历只能操作一次, 再一次操作需要重新获取流对象
        threeHighCaloricDishNamesList.forEach(System.out::println);
    }

    public static List<Dish> filterTest(List<Dish> menu) {
        List<Dish> result = menu.stream()
                // 筛选器
                .filter(Dish::isVegetarian)
                // 去掉重复的元素
                .distinct()
                // 从第2个元素开始, 和limit()函数互补
                .skip(2)
                // 打包成list
                .collect(Collectors.toList());
        return result;
    }

    /**
     * 筛选字符串中的单词并统计
     *
     * @param data
     * @return
     */
    public static List<String> stringTest(List<String> data) {
        var result = data.stream()
                // 分割字符串
                .map(word -> word.split(""))
                // 扁平化流
                .flatMap(Arrays::stream)
                // 去掉重复元素
                .distinct()
                // 打包成list
                .collect(Collectors.toList());
        return result;
    }

    /**
     * 判断存在
     *
     * @param menu
     */
    public static void match(List<Dish> menu) {
        // 是否全部符合Lambda表达式
        boolean isHeavy = menu.stream().allMatch(dish -> dish.getCalories() > 1000);
        // 是否全部不符合Lambda表达式
        isHeavy = menu.stream().noneMatch(dish -> dish.getCalories() > 1000);
        // 是否至少有一项符合Lambda表达式
        isHeavy = menu.stream().anyMatch(dish -> dish.getCalories() > 1000);
    }

    /**
     * 查找元素
     * Optional<Dish> 防止返回值为空导致的问题
     *
     * @param menu
     * @return
     */
    public static void search(List<Dish> menu) {
        menu.stream()
                // 筛选
                .filter(Dish::isVegetarian)
                // 找到所有符合要求的值
                .findAny()
                // 如果不为空则执行Lambda表达式
                .ifPresent(System.out::println);
    }

    public static void sunAndMul(List<Integer> list) {
        // 求和
        int sum = list.stream().reduce(0, Integer::sum);
        // 求积
        int mul = list.stream().reduce(1, (a, b) -> a * b);
        // 最大值, 最小值和总数
        Optional<Integer> max = list.stream().reduce(Integer::max);
        Optional<Integer> min = list.stream().reduce(Integer::min);
        // 遍历
        list.forEach(System.out::println);
    }

    /**
     * 流转换
     */
    public void changeStream(List<Dish> menu) {
        IntStream intStream = menu.stream().mapToInt(Dish::getCalories);
        Stream<Integer> stream = intStream.boxed();
    }

}
