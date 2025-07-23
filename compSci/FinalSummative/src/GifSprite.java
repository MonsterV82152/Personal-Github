import java.awt.Image;
import java.awt.Toolkit;

public class GifSprite {
    public Image image;
    public int x, y;
    public int width, height;

    /**
     * Constructor for creating a GifSprite.
     * @param imageName The name of the image file
     * @param x The x-coordinate of the sprite
     * @param y The y-coordinate of the sprite
     * @param width The width of the sprite
     * @param height The height of the sprite
     */
    public GifSprite(String imageName, int x, int y, int width, int height) {
        this.image = Toolkit.getDefaultToolkit().getImage(imageName);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
}