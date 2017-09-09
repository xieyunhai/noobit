package pattern.factory.simple;

import pattern.factory.Pizza;

/**
 * @author noobit
 * @date 2017-08-06 15:47 星期日
 */
public class PizzaStore {
    SimplePizzaFactory simplePizzaFactory;

    public PizzaStore(SimplePizzaFactory simplePizzaFactory) {
        this.simplePizzaFactory = simplePizzaFactory;
    }

    public void orderPizzaBySimpleFactory(String type) {
        Pizza pizza = simplePizzaFactory.createPizza(type);
        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();
    }
}
