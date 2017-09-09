package pattern.strategy;

import org.junit.Test;
import pattern.strategy.port.FlyBehavior;
import pattern.strategy.port.QuackBehavior;

import static org.junit.Assert.*;

/**
 * @author noobit
 * @date 2017-08-05 18:06 星期六
 */
public class MallardDuckTest {
    @Test
    public void display() throws Exception {
        QuackBehavior quackBehavior = new Quack();
        FlyBehavior flyBehavior = new FlyWithWings();
        FlyBehavior flyBehavior1 = new FlyNoWay();

        MallardDuck mallardDuck = new MallardDuck(flyBehavior, quackBehavior);
        mallardDuck.display();

        mallardDuck.setFlyBehavior(flyBehavior1);
        mallardDuck.display();
    }

}