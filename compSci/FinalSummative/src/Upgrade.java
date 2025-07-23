import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.Callable;
import javax.imageio.ImageIO;

public class Upgrade {
    public static int SINGLE = 0;
    public static int MULT = 1;
    public String name;
    public int type;
    public int price;
    public double multiplier;
    public int count;
    public BufferedImage image;
    public Callable<Void> action;
    public int drawX;         // Current x-position for smooth sliding
    public int targetX;       // Target x-position to slide to
    public float alpha = 0f;  // Transparency (0.0 = fully transparent, 1.0 = fully visible)

    public Upgrade(String name, int price, double multiplier, String imageName, Callable<Void> action) {
        this.name = name;
        this.price = price;
        this.count = 0;
        this.type = MULT;
        this.multiplier = multiplier;

        this.drawX = -10000;
        this.targetX = 0;
        this.alpha = 0f;

        try {
            this.image = ImageIO.read(new File(imageName)); // Loads and blocks until ready
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.action = action;
    }

    public Upgrade(String name, int price, String imageName, Callable<Void> action) {
        this.name = name;
        this.price = price;
        this.count = 0;
        this.type = SINGLE;

        this.drawX = -10000;
        this.targetX = 0;
        this.alpha = 0f;
        try {
            this.image = ImageIO.read(new File(imageName)); // Loads and blocks until ready
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.action = action;
    }

    public void update() {
        if (type == MULT) {
            price = (int) (price * multiplier);
        }
        count++;
        try {
            action.call();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
