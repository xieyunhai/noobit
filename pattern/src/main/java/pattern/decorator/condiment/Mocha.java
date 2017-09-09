package pattern.decorator.condiment;

import pattern.decorator.beverage.Beverage;

/**
 * @author noobit
 * @date 2017-08-05 22:53 星期六
 */
public class Mocha extends CondimentDecorator {
    private Beverage beverage;

    public Mocha(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public double cost() {
        return beverage.cost() + 0.2;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + ", Mocha";
    }
}
