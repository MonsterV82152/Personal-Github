import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class DVDHandler {
    int x, y;
    double dx, dy;
    final int dvdWidth = 80;
    final int dvdHeight = 50;
    double[] angles = {Math.PI/3, Math.PI*2/3, Math.PI*4/3, Math.PI*5/3}; // 60, 120, 240, 300 degrees in radians
    int value, velocity = 5;
    int frames = 60;
    Image image;

    public DVDHandler() {
        Random random = new Random();
        x = random.nextInt(1000 - dvdWidth);
        y = random.nextInt(700 - dvdHeight);
        double angle = angles[random.nextInt(0, 4)]; // Random angle in radians
        dx = Math.sin(angle) * velocity;
        dy = Math.cos(angle) * velocity;
        try {
            image = ImageIO.read(new File("images/dvd.png")); // adjust path as needed
        } catch (IOException e) {
            System.out.println("Image could not be loaded.");
            e.printStackTrace();
        }
        
    }

    public int update() {
        x += dx;
        y += dy;
        int sum = 0;

        // Check boundaries and bounce if necessary
        if (x < 0 || x + dvdWidth > 1000) {
            sum++;
            dx = -dx; // Reverse direction horizontally
            sum*=10;
        }
        if (y < 0 || y + dvdHeight > 680) {
            sum++;
            dy = -dy; // Reverse direction vertically
            sum*=10;
        }
        return sum;
    }

    public boolean isAlive() {
        return frames > 0;
    }

    public void draw(Graphics g, JPanel parentCanvas) {
        g.drawImage(image, x, y, dvdWidth, dvdHeight, parentCanvas);
    }
}
