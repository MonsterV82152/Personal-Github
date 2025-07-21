import java.awt.*;

public class FloatingNumber implements FloatingNumberDisplay {
    int x, y;
    int value;
    int frames = 60;
    Color color = Color.BLACK;

    public FloatingNumber(int x, int y, int value) {
        this.x = x;
        this.y = y;
        this.value = value;
    }

    public void update() {
        y -= 1;
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
