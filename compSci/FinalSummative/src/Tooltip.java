import java.awt.*;

public class Tooltip {
    private String text, text2;
    private int x, y;
    private boolean no;

    public Tooltip(String text, String text2, int x, int y, boolean no) {
        this.text = text;
        this.text2 = text2;
        this.x = x;
        this.y = y;
        this.no = no;
    }

    public void draw(Graphics g) {
        if (text == null || text.isEmpty()) return;

        FontMetrics fm = g.getFontMetrics();
        int width = Math.max(fm.stringWidth(text) + 10, fm.stringWidth(text2) + 10);
        int height = fm.getHeight()*2 + 4;
        
        g.setColor(new Color(0, 0, 0, 170));
        g.fillRoundRect(x, y, width, height, 8, 8);
        g.setColor(no ? Color.RED : Color.WHITE);
        g.drawString(text, x + 5, y-2 + fm.getHeight());
        g.drawString(text2, x + 5, y-2 + fm.getHeight()*2);

    }
}