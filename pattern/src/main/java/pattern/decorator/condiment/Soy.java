package pattern.decorator.condiment;

import pattern.decorator.beverage.Beverage;

/**
 * @author noobit
 * @date 2017-08-05 22:53 星期六
 */
public class Soy extends CondimentDecorator {
    private Beverage beverage;

    public Soy(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public double cost() {
        return beverage.cost() + 0.15;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + ", Soy";
    }
}
