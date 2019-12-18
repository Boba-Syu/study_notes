package cn.bobasyu.java8.beforelearning;

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
