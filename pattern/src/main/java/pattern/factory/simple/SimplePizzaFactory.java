package pattern.factory.simple;

import pattern.factory.*;

/**
 * @author noobit
 * @date 2017-08-06 15:29 星期日
 */
public class SimplePizzaFactory {

    public Pizza createPizza(String type) {
        Pizza pizza;

        switch (type) {
            case "cheese":
                pizza = new CheesePizza();
                break;
            case "pepperoni":
                pizza = new PepperoniPizza();
                break;
            case "clam":
                pizza = new ClamPizza();
                break;
            case "veggie":
                pizza = new VeggiePizza();
                break;
            default:
                pizza = null;
        }
        return pizza;
    }
}
