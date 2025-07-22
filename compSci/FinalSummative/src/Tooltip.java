import java.awt.*;

public class Tooltip {
    private String text, text2;
    private int x, y;

    public Tooltip(String text, String text2, int x, int y) {
        this.text = text;
        this.text2 = text2;
        this.x = x;
        this.y = y;
    }

    public void draw(Graphics g) {
        if (text == null || text.isEmpty()) return;

        FontMetrics fm = g.getFontMetrics();
        int width = fm.stringWidth(text) + 10;
        int height = fm.getHeight()*2 + 4;

        g.setColor(new Color(0, 0, 0, 170));
        g.fillRoundRect(x, y, width, height, 8, 8);
        g.setColor(Color.WHITE);
        g.drawString(text, x + 5, y-2 + fm.getHeight());
        g.drawString(text2, x + 5, y-2 + fm.getHeight()*2);

    }
}