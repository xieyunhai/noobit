package pattern.decorator.beverage;

/**
 * @author noobit
 * @date 2017-08-05 22:52 星期六
 */
public class Espresso extends Beverage {
    public Espresso(String description) {
        super(description);
    }

    public Espresso() {}

    @Override
    public double cost() {
        return 1.99;
    }
}
