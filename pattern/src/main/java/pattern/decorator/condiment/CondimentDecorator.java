package pattern.decorator.condiment;

import pattern.decorator.beverage.Beverage;

/**
 * @author noobit
 * @date 2017-08-05 22:45 星期六
 */
public abstract class CondimentDecorator extends Beverage {
    public abstract String getDescription();
}
