import java.awt.Rectangle;
import java.util.concurrent.Callable;

public class Upgrade {
    public static int SINGLE = 0;
    public static int MULT = 1;
    public String name;
    public int type;
    public int price;
    public double multiplier;
    public int count;
    public Rectangle bounds;
    public Callable<Void> action;
    
    public Upgrade(String name, int price, double multiplier, Rectangle bounds, Callable<Void> action) {
        this.name = name;
        this.price = price;
        this.count = 0;
        this.type = MULT;
        this.multiplier = multiplier;
        this.bounds = bounds;
        this.action = action;
    }
    public Upgrade(String name, int price, Rectangle bounds, Callable<Void> action) {
        this.name = name;
        this.price = price;
        this.count = 0;
        this.type = SINGLE;
        this.bounds = bounds;
        this.action = action;
    }

    public void update() {
        if (type == MULT) {
            price = (int)(price*multiplier);
        }
        count++;
        try {
            action.call();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }



}
