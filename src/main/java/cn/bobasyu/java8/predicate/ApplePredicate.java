package cn.bobasyu.java8.predicate;

/**
 * @author Boba
 */
@FunctionalInterface
public interface ApplePredicate {
    /**
     * Apple的筛选标准
     *
     * @param apple
     * @return
     */
    boolean test(Apple apple);
}
