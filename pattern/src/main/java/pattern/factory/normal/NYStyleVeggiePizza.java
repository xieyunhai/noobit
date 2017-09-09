package pattern.factory.normal;

import pattern.factory.Pizza;

/**
 * @author noobit
 * @date 2017-08-06 16:17 星期日
 */
public class NYStyleVeggiePizza extends Pizza {
    public NYStyleVeggiePizza() {
        name = "NY style Sauce and Veggie Pizza";
        dough = "Thin Crust Dough";
        sauce = "Marinara Sauce";

        toppings.add("Grated");
        toppings.add("Cheese");
    }
}
