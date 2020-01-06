package cn.bobasyu.java8.optional;

import java.util.Optional;

/**
 * @author Boba
 */
public class Person {
    private Optional<Car> car;

    public Optional<Car> getCar() {
        return car;
    }

    public void setCar(Optional<Car> car) {
        this.car = car;
    }

}
