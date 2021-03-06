package pattern.factory.simple;

import pattern.factory.Pizza;

/**
 * @author noobit
 * @date 2017-08-06 15:40 星期日
 */
public class VeggiePizza extends Pizza {
    @Override
    public void prepare() {
        System.out.println("Veggie Pizza prepare...");
    }

    @Override
    public void bake() {
        System.out.println("Veggie Pizza bake...");
    }

    @Override
    public void cut() {
        System.out.println("Veggie Pizza cut...");
    }

    @Override
    public void box() {
        System.out.println("Veggie Pizza box...");
    }
}
