import java.awt.*;

public class FloatingNumber implements FloatingNumberDisplay {
    int x, y;
    int value, velocityY = 15, velocityX = 0;
    int frames = 60;
    boolean crit;
    Color color = Color.BLACK;

    /**
     * Constructor for FloatingNumber.
     * @param x The x-coordinate for the floating number
     * @param y The y-coordinate for the floating number
     * @param value The value of the floating number
     * @param crit Whether this is a critical hit (affects color and size)
     */
    public FloatingNumber(int x, int y, int value, boolean crit) {
        this.x = x;
        this.y = y;
        this.value = value;
        this.crit = crit;
        if (crit) {
            this.color = Color.RED; // Critical hits are red
        }
        this.velocityX = (int) (Math.random() * 10 - 5); // Random horizontal velocity
    }

    /**
     * Updates the position of the floating number.
     * Moves it upwards and applies gravity.
     */
    public void update() {
        y -= velocityY;
        velocityY -= 1; // Gravity effect
        x += velocityX; // Horizontal movement
        frames--;
    }

    /**
     * Checks if the floating number is still alive (i.e., has frames left).
     * @return True if the floating number is still alive, false otherwise
     */
    public boolean isAlive() {
        return frames > 0;
    }

    /**
     * Draws the floating number on the given Graphics context.
     * @param g The Graphics context to draw on
     */
    public void draw(Graphics g) {
        g.setColor(color);
        if (!crit) {
            g.setFont(new Font("Arial", Font.PLAIN, 16));
        } else {
            g.setFont(new Font("Arial", Font.BOLD, 20)); // Critical hits are larger
        }
        g.drawString("+" + value, x, y); // Draws the value with a "+" sign
    }
}
