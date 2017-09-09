package pattern.factory;

import lombok.Getter;

import java.util.ArrayList;

/**
 * @author noobit
 * @date 2017-08-06 15:34 星期日
 */
@Getter
public abstract class Pizza {
    protected String name;
    protected String dough;
    protected String sauce;
    protected ArrayList<String> toppings = new ArrayList<>();

    public void prepare() {
        System.out.println("Pizza prepare... " + name);
        System.out.println("Tossing dough... " + dough);
        System.out.println("Adding sauce... " + sauce);
        System.out.println("Adding toppings...");
        toppings.forEach((topping) -> {
            System.out.print("   ");
            System.out.print(topping);
        });
        System.out.println("");
    }

    public void bake() {
        System.out.println("Pizza bake... ");
    }

    public void cut() {
        System.out.println("Pizza cut...");
    }

    public void box() {
        System.out.println("Pizza box...");
    }
}
