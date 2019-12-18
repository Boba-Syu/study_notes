package cn.bobasyu.java8.beforelearning;

/**
 * 筛选重苹果
 *
 * @author Boba
 */
public class AppleHeavyPredicate implements ApplePredicate {
    @Override
    public boolean test(Apple apple) {
        return apple.getWeight() > 150;
    }
}
