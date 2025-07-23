import java.awt.*;

public class Tooltip {
    private String text, text2;
    private int x, y;
    private boolean no;

    /**
     * Constructor for Tooltip.
     * @param text The main text to display
     * @param text2 The secondary text to display
     * @param x The x-coordinate for the tooltip
     * @param y The y-coordinate for the tooltip
     * @param no A flag indicating if the tooltip is "no"
     */
    public Tooltip(String text, String text2, int x, int y, boolean no) {
        this.text = text;
        this.text2 = text2;
        this.x = x;
        this.y = y;
        this.no = no;
    }

    /**
     * Draws the tooltip on the given Graphics context.
     * @param g The Graphics context to draw on
     */
    public void draw(Graphics g) {
        if (text == null || text.isEmpty()) return; // Do not draw if text is null or empty

        FontMetrics fm = g.getFontMetrics();
        int width = Math.max(fm.stringWidth(text) + 10, fm.stringWidth(text2) + 10);
        int height = fm.getHeight()*2 + 4; // Height for two lines of text
        
        g.setColor(new Color(0, 0, 0, 170));
        g.fillRoundRect(x, y, width, height, 8, 8);
        g.setColor(no ? Color.RED : Color.WHITE); // Set color based on 'no' flag - Too expensive
        g.drawString(text, x + 5, y-2 + fm.getHeight()); // Draw main text
        g.drawString(text2, x + 5, y-2 + fm.getHeight()*2); // Draw secondary text

    }
}