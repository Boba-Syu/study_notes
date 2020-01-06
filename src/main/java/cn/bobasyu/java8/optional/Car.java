package cn.bobasyu.java8.optional;

import java.util.Optional;

/**
 * @author Boba
 */
public class Car {
    private Optional<Insurance> insurance;

    public Optional<Insurance> getInsurance() {
        return insurance;
    }

    public void setInsurance(Optional<Insurance> insurance) {
        this.insurance = insurance;
    }

}
