package pattern.decorator.beverage;

/**
 * @author noobit
 * @date 2017-08-05 22:53 星期六
 */
public class Decaf extends Beverage {
    public Decaf(String description) {
        super(description);
    }

    public Decaf() {}

    @Override
    public double cost() {
        return 1.05;
    }
}
