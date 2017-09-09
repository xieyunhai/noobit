package pattern.factory.normal;

import pattern.factory.Pizza;

/**
 * @author noobit
 * @date 2017-08-06 16:10 星期日
 */
public abstract class PizzaStore {
    public Pizza orderPizza(String type) {
        Pizza pizza;
        pizza = createPizza(type);

        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();

        return pizza;
    }

    abstract Pizza createPizza(String type);


}
