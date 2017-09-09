package pattern.decorator.beverage;

/**
 * @author noobit
 * @date 2017-08-05 22:49 星期六
 */
public class HouseBlend extends Beverage {
    public HouseBlend(String description) {
        super(description);
    }

    public HouseBlend() {}

    @Override
    public double cost() {
        return 0.89;
    }
}
