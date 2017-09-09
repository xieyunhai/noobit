package pattern.strategy;

import pattern.strategy.port.FlyBehavior;
import pattern.strategy.port.QuackBehavior;

/**
 * @author noobit
 * @date 2017-08-05 17:28 星期六
 */
public class MallardDuck extends Animal {
    public MallardDuck(FlyBehavior flyBehavior, QuackBehavior quackBehavior) {
        super(flyBehavior, quackBehavior);
    }

    public void display() {
        performFly();
        performQuack();
        System.out.println("I'm a real Mallard duck!");
    }
}
