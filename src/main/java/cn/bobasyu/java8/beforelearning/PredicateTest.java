package cn.bobasyu.java8.beforelearning;


import java.util.ArrayList;
import java.util.List;

/**
 * 通过提供删选接口的实现类来提供不同的删选方式
 * 每一个删选方式都需要额外写一个实现类
 *
 * @author Boba
 */
public class PredicateTest {
    /**
     * @param inventory
     * @param applePredicate 提供删选方法
     */
    public List<Apple> filterApples(List<Apple> inventory, ApplePredicate applePredicate) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            // 根据删选方法删选苹果
            if (applePredicate.test(apple)) {
                result.add(apple);
            }
        }
        return result;
    }

    /**
     * 通过匿名类来筛选苹果
     *
     * @param inventory
     * @return
     */
    public List<Apple> filterApples(List<Apple> inventory) {
        return filterApples(inventory, new ApplePredicate() {

            @Override
            public boolean test(Apple apple) {
                return "red".equals(apple.getColor());
            }
        });
    }

    /**
     * 通过lambda表达式筛选
     *
     * @param inventory
     * @return
     */
    public List<Apple> filterApplesByLambda(List<Apple> inventory) {
        List<Apple> result = filterApples(inventory, (Apple apple) -> "red".equals(apple.getColor()));
        return result;
    }
}
