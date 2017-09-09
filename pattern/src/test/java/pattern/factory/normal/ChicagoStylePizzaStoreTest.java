package pattern.factory.normal;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author noobit
 * @date 2017-08-06 16:42 星期日
 */
public class ChicagoStylePizzaStoreTest {
    @Test
    public void orderPizzaByNormalFactory() throws Exception {
        ChicagoStylePizzaStore chicagoStylePizzaStore = new ChicagoStylePizzaStore();
        chicagoStylePizzaStore.orderPizzaByNormalFactory("cheese");
    }

}