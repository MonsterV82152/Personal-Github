import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class GifSprite {
    public Image image;
    public int x, y;
    public int width, height;

    public GifSprite(String imageName, int x, int y, int width, int height) {
        this.image = Toolkit.getDefaultToolkit().getImage(imageName);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
}