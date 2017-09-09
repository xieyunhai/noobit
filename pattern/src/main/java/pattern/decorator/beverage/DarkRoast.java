package pattern.decorator.beverage;

/**
 * @author noobit
 * @date 2017-08-05 22:51 星期六
 */
public class DarkRoast extends Beverage {

    public DarkRoast(String description) {
        super(description);
    }

    public DarkRoast() {}

    @Override
    public double cost() {
        return 0.99;
    }
}
