package pattern.factory.simple;

import pattern.factory.Pizza;

/**
 * @author noobit
 * @date 2017-08-06 15:40 星期日
 */
public class CheesePizza extends Pizza {
    @Override
    public void prepare() {
        System.out.println("Cheese Pizza prepare...");
    }

    @Override
    public void bake() {
        System.out.println("Cheese Pizza bake...");
    }

    @Override
    public void cut() {
        System.out.println("Cheese Pizza cut...");
    }

    @Override
    public void box() {
        System.out.println("Cheese Pizza box...");
    }
}
