package pattern.decorator;

import org.junit.Test;
import pattern.decorator.beverage.Beverage;
import pattern.decorator.beverage.Espresso;
import pattern.decorator.condiment.Mocha;
import pattern.decorator.condiment.Whip;

import static org.junit.Assert.*;

/**
 * @author noobit
 * @date 2017-08-05 23:10 星期六
 */
public class ExampleTest {
    @Test
    public void display() throws Exception {
        Beverage beverage = new Espresso("Espresso");
        beverage = new Mocha(beverage);
        beverage = new Mocha(beverage);
        beverage = new Whip(beverage);

        System.out.println(beverage.getDescription() + " $" + beverage.cost());
    }

}