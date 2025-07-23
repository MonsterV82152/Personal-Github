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

    /**
     * Constructor for DVDHandler.
     */
    public DVDHandler() {
        Random random = new Random();
        x = random.nextInt(1000 - dvdWidth); // Random initial position within bounds
        y = random.nextInt(700 - dvdHeight); // Random initial position within bounds
        double angle = angles[random.nextInt(0, 4)]; // Random angle in radians
        dx = Math.sin(angle) * velocity; // Horizontal velocity based on angle
        dy = Math.cos(angle) * velocity; // Vertical velocity based on angle
        try {
            image = ImageIO.read(new File("images/dvd.png")); // adjust path as needed
        } catch (IOException e) {
            System.out.println("Image could not be loaded.");
            e.printStackTrace();
        }
        
    }

    /**
     * Updates the position of the DVD.
     * @return The score for this update (higher if it bounced)
     */
    public int update() {
        x += dx; // Move horizontally
        y += dy; // Move vertically
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
        return sum; // Return the score for this update, 0 if no bounce occurred
    }
    /**
     * Draws the DVD on the given Graphics context.
     * @param g The Graphics context to draw on
     * @param parentCanvas The parent JPanel for proper rendering
     */
    public void draw(Graphics g, JPanel parentCanvas) {
        g.drawImage(image, x, y, dvdWidth, dvdHeight, parentCanvas);
    }
}
