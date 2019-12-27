package cn.bobasyu.java8.stream;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Boba
 */
public class StreamCollection {

    /**
     * 分类打包
     *
     * @param menu
     * @return
     */
    public static void group(List<Dish> menu) {
        // 根据菜的类型分类
        Map<Dish.Type, List<Dish>> dishesByDishType = menu.stream().collect(Collectors.groupingBy(Dish::getType));
        // 根据菜的卡路里含量登记分类
        Map<String, List<Dish>> dishesByCaloriesLevel = menu.stream().collect(Collectors.groupingBy(StreamCollection::caloriesLevel));
        // 二重分类
        Map<Dish.Type, Map<String, List<Dish>>> dishes = menu.stream()
                // 第一层分类
                .collect(Collectors.groupingBy(Dish::getType,
                        // 第二层分类
                        Collectors.groupingBy(StreamCollection::caloriesLevel)));
        // 找出每个类型中卡路里含量最大的菜
        Map<Dish.Type, Dish> mostCaloriesByType = menu.stream()
                // 按菜类型分组
                .collect(Collectors.groupingBy(Dish::getType,
                        // 获取分组后每个分组中最大的菜, 封装在Optional中
                        Collectors.collectingAndThen(Collectors.maxBy(Comparator.comparingInt(Dish::getCalories)),
                                // 从Optional中获取Dish对象
                                Optional::get)));
    }

    /**
     * 返回菜的卡路里等级
     *
     * @param dish
     * @return
     */
    public static String caloriesLevel(Dish dish) {
        int highCalories = 400;
        int normalCalories = 300;
        if (dish.getCalories() >= highCalories) {
            return "High Calories";
        } else if (dish.getCalories() >= normalCalories) {
            return "Normal Calories";
        } else {
            return "Low Calories";
        }
    }

    /**
     * 流汇总
     *
     * @param menu
     */
    public static void summing(List<Dish> menu) {
        // 求总价, 以下表达式等价
        int totalCalories = menu.stream().mapToInt(Dish::getCalories).sum();
        totalCalories = menu.stream().collect(Collectors.summingInt(Dish::getCalories));
        totalCalories = menu.stream().map(Dish::getCalories).reduce(0, Integer::sum);
        totalCalories = menu.stream().collect(Collectors.reducing(0, Dish::getCalories, Integer::sum));
        // 求平均值
        double avgCalories = menu.stream().collect(Collectors.averagingInt(Dish::getCalories));
        // 连接字符串, 无间隔符
        String shotMenu = menu.stream().map(Dish::getName).collect(Collectors.joining());
        // 连接字符串, 用逗号隔开
        shotMenu = menu.stream().map(Dish::getName).collect(Collectors.joining(","));
    }
}
