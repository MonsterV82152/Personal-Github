import java.awt.*;

public class FloatingNumber implements FloatingNumberDisplay {
    int x, y;
    int value, velocityY = 15, velocityX = 0;
    int frames = 60;
    Color color = Color.BLACK;

    public FloatingNumber(int x, int y, int value) {
        this.x = x;
        this.y = y;
        this.value = value;
        this.velocityX = (int) (Math.random() * 10 - 5); // Random horizontal velocity
    }

    public void update() {
        y -= velocityY;
        velocityY -= 1; // Gravity effect
        x += velocityX; // Horizontal movement
        frames--;
    }

    public boolean isAlive() {
        return frames > 0;
    }

    public void draw(Graphics g) {
        g.setColor(color);
        g.setFont(new Font("Arial", Font.PLAIN, 20));
        g.drawString("+" + value, x, y);
    }
}
