package pattern.decorator.beverage;

import lombok.Data;
import lombok.Getter;

/**
 * @author noobit
 * @date 2017-08-05 22:38 星期六
 */
@Getter
public abstract class Beverage {
    String description;

    public Beverage(String description) {
        this.description = description;
    }

    public Beverage() {}

    public abstract double cost();
}
