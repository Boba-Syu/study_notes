package cn.bobasyu.java8.predicate;

/**
 * 删选绿色的苹果
 *
 * @author Boba
 */
public class AppleGreenColorPredicate implements ApplePredicate {
    @Override
    public boolean test(Apple apple) {
        return "green".equals(apple.getColor());
    }
}
