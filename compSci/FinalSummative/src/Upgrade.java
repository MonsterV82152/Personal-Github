import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.Callable;
import javax.imageio.ImageIO;

public class Upgrade {
    public static int SINGLE = 0; // Single-use upgrade
    public static int MULT = 1;    // Multiplier upgrade
    public String name; // Name of the upgrade
    public int type; // Type of the upgrade (single-use or multiplier)
    public int price; // Price of the upgrade
    public double multiplier; // Multiplier for multiplier upgrades
    public int count; // Count of how many times this upgrade has been purchased
    public BufferedImage image; // Image representing the upgrade
    public Callable<Void> action; // Action to perform when the upgrade is used
    public int drawX;         // Current x-position for smooth sliding
    public int targetX;       // Target x-position to slide to
    public float alpha = 0f;  // Transparency (0.0 = fully transparent, 1.0 = fully visible)

    /**
     * Constructor for multiplier upgrades.
     * @param name The name of the upgrade
     * @param price The price of the upgrade
     * @param multiplier The multiplier effect of the upgrade
     * @param imageName The path to the image file for the upgrade
     * @param action The action to perform when this upgrade is used
     */
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

    /**
     * Constructor for single-use upgrades.
     * @param name The name of the upgrade
     * @param price The price of the upgrade
     * @param imageName The path to the image file for the upgrade
     * @param action The action to perform when this upgrade is used
     */
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

    /**
     * Updates the upgrade state.
     * If it's a multiplier upgrade, it increases the price by the multiplier.
     * If it's a single-use upgrade, it executes the action and increments the count.
     */
    public void update() {
        if (type == MULT) {
            price = (int) (price * multiplier); // Increase price by multiplier
        }
        count++; // Increment count for this upgrade
        try {
            action.call(); // Execute the action associated with this upgrade
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
