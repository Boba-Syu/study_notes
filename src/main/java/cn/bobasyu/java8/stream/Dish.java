package cn.bobasyu.java8.stream;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 菜单
 *
 * @author Boba
 */
@Data
@AllArgsConstructor
public class Dish {
    private String name;
    private boolean vegetarian;
    private int calories;
    private Type type;

    public enum Type {
        /**
         * 肉
         */
        MEAT,
        /**
         * 鱼
         */
        FISH,
        /**
         * 其他
         */
        OTHER
    }
}
