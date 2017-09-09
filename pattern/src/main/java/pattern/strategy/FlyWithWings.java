package pattern.strategy;

import pattern.strategy.port.FlyBehavior;

/**
 * @author noobit
 * @date 2017-08-05 17:38 星期六
 */
public class FlyWithWings implements FlyBehavior {
    @Override
    public void fly() {
        System.out.println("I'm fly with wings");
    }
}
