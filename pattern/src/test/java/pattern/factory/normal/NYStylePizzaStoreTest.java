package pattern.factory.normal;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author noobit
 * @date 2017-08-06 16:49 星期日
 */
public class NYStylePizzaStoreTest {
    @Test
    public void orderPizzaByNormalFactory() throws Exception {
        NYStylePizzaStore nyStylePizzaStore = new NYStylePizzaStore();
        nyStylePizzaStore.orderPizzaByNormalFactory("cheese");

        nyStylePizzaStore.orderPizzaByNormalFactory("clam");
    }

}