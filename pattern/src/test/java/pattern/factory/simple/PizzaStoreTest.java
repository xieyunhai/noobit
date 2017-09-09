package pattern.factory.simple;

import org.junit.Test;


/**
 * @author noobit
 * @date 2017-08-06 16:35 星期日
 */
public class PizzaStoreTest {
    @Test
    public void orderPizzaBySimpleFactory() throws Exception {
        PizzaStore pizzaStore = new PizzaStore(new SimplePizzaFactory());

        pizzaStore.orderPizzaBySimpleFactory("cheese");

        pizzaStore.orderPizzaBySimpleFactory("clam");
    }

}