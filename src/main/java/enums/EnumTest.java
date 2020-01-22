package enums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Java枚举学习
 *
 * @author Boba
 */
public enum EnumTest {

    /**
     * TEST_01
     */
    TEST_01("test01"),
    /**
     * TEST_02
     */
    TEST_02("test02"),
    /**
     * TEST_01
     */
    TEST_03("test03");

    private String name;

    EnumTest(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
    
    public static void main(String[] args) {
        List<EnumTest> enumTests = new ArrayList<>(Arrays.asList(EnumTest.TEST_01, EnumTest.TEST_02, EnumTest.TEST_03));
        enumTests.stream().map(EnumTest::getName).forEach(System.out::println);
    }
}
