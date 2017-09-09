package pattern.factory.normal;

import pattern.factory.Pizza;

/**
 * @author noobit
 * @date 2017-08-06 16:32 星期日
 */
public class ChicagoStyleClamPizza extends Pizza {
    public ChicagoStyleClamPizza() {
        name = "Chicago style Sauce and Clam Pizza";
        dough = "Thin Crust Dough";
        sauce = "Marinara Sauce";

        toppings.add("Grated");
        toppings.add("Cheese");
    }

    @Override
    public void cut() {
        System.out.println("Chicago Cheese Pizza cut...");
    }
}
