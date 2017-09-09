package pattern.strategy;

import pattern.strategy.port.QuackBehavior;

/**
 * @author noobit
 * @date 2017-08-05 17:39 星期六
 */
public class Quack implements QuackBehavior {
    @Override
    public void quack() {
        System.out.println("I'm quack!");
    }
}
