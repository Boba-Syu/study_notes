package cn.bobasyu.java8.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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
}
