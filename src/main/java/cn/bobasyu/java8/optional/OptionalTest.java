package cn.bobasyu.java8.optional;

import java.util.Optional;

/**
 * Optional使用
 *
 * @author Boba
 */
public class OptionalTest {

    public static void main(String[] args) {
        Optional<Insurance> optionalInstance = Optional.of(new Insurance());
        optionalInstance.get().setName("Test");

        Car car = new Car();
        car.setInsurance(optionalInstance);
        Optional<Car> optionalCar = Optional.ofNullable(car);

        Person person = new Person();
        person.setCar(optionalCar);
        Optional<Person> optionalPerson = Optional.empty();

        test(optionalPerson);
    }

    private static void test(Optional<Person> person) {
        String name = person.flatMap(Person::getCar)
                .flatMap(Car::getInsurance)
                .map(Insurance::getName)
                .orElse("Unknown");
        System.out.println(name);
    }
}
