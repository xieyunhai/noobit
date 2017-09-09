package pattern.strategy;


import lombok.Setter;
import pattern.strategy.port.FlyBehavior;
import pattern.strategy.port.QuackBehavior;

/**
 * @author noobit
 * @date 2017-08-05 17:16 星期六
 */
@Setter
public abstract class Animal {

    private FlyBehavior flyBehavior;
    private QuackBehavior quackBehavior;

    public Animal() { }

    Animal(FlyBehavior flyBehavior, QuackBehavior quackBehavior) {
        this.flyBehavior = flyBehavior;
        this.quackBehavior = quackBehavior;
    }

    void performQuack() {
        quackBehavior.quack();
    }

    void performFly() {
        flyBehavior.fly();
    }
}
