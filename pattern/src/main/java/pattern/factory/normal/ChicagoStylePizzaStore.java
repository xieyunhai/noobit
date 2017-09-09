package pattern.factory.normal;

import pattern.factory.Pizza;

/**
 * @author noobit
 * @date 2017-08-06 16:15 星期日
 */
public class ChicagoStylePizzaStore extends PizzaStore {
    @Override
    public Pizza createPizza(String type) {
        Pizza pizza;

        switch (type) {
            case "cheese":
                pizza = new ChicagoStyleCheesePizza();
                break;
            case "clam":
                pizza = new ChicagoStyleClamPizza();
                break;
            default:
                pizza = null;
        }
        return pizza;
    }

    public void orderPizzaByNormalFactory(String type) {
        orderPizza(type);
    }
}
