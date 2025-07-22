import java.awt.Graphics;

public interface FloatingNumberDisplay {
    void draw(Graphics g);
    boolean isAlive(); 
    void update();   
}
