import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;

public class DVDHandler {
    int x, y;
    double dx, dy;
    final int dvdWidth = 50;
    final int dvdHeight = 50;
    int value, velocityY = 15, velocityX = 0;
    int frames = 60;
    Image image;

    public DVDHandler() {
        this.velocityX = (int) (Math.random() * 10 - 5); // Random horizontal velocity
        Random random = new Random();
        x = random.nextInt(1000 - dvdWidth);
        y = random.nextInt(700 - dvdHeight);
        double angle = random.nextDouble() * 2 * Math.PI; // Random angle in radians
        dx = Math.cos(angle);
        dy = Math.sin(angle);
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
            dx = -dx; // Reverse direction horizontally
            sum += 10;
        }
        if (y < 0 || y + dvdHeight > 700) {
            dy = -dy; // Reverse direction vertically
            sum += 10;
        }
        return sum;
    }

    public boolean isAlive() {
        return frames > 0;
    }

    public void draw(Graphics g, Canvas parentCanvas) {
        g.drawImage(image, x, y, dvdWidth, dvdHeight, parentCanvas);
    }
}
