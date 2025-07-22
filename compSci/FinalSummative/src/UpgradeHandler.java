import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;

public class UpgradeHandler {
    private int dps;
    private int dpsMult;
    private int dpc;
    private int dpcMult;
    private Canvas parentCanvas;
    private List<Upgrade> upgrades;
    private List<GifSprite> sprites;

    public UpgradeHandler(Canvas parentCanvas) {
        this.parentCanvas = parentCanvas;
        dpc = 1;
        dpcMult = 1;
        dps = 0;
        dpsMult = 1;
        upgrades = new ArrayList<>();
        upgrades.add(new Upgrade("Upgrade Clicker", 10, 1.5,
                "Personal Github\\compSci\\FinalSummative\\images\\clickerIcon.png", () -> {
                    dpc++;
                    return null;
                }));
        upgrades.add(new Upgrade("Hydraulic Press", 100, 1.5,
                "Personal Github\\compSci\\FinalSummative\\images\\hydraulicPress.png", () -> {
                    dps += 100;
                    // if (sprites.contains(new GifSprite(null, 0, 0))) {
                    //     sprites.remove(new GifSprite(null, 0, 0));
                    // }
                    return null;
                }));
        // upgrades.add(new Upgrade("Subway Surfers", 200, () -> {
        // dps+=200;
        // return null;
        // }));
        // upgrades.add(new Upgrade("Lofi Beats", 300, () -> {
        // dps+=300;
        // return null;
        // }));
    }

    public void draw(Graphics g) {
        int count = 0;
        int x = 413, y = 380;
        for (Upgrade element : upgrades) {
            if (element.type == Upgrade.SINGLE && element.count > 0) {
                continue;
            } else {
                g.setColor(Color.cyan);
                g.drawRect(x + count * 45, y, 40, 40);
                g.drawImage(element.image, x + count * 45 + 1, y + 1, 39, 39, parentCanvas);
            }
            count++;
        }
    }

    public void mouseEvent(MouseEvent e) {
        
        int x = 413;
        int y = 380;
        int mouseX = e.getX();
        int mouseY = e.getY();
        System.out.println("Click at: " + mouseX + ", " + mouseY);

        for (int i = 0; i < upgrades.size(); i++) {
            Upgrade upgrade = upgrades.get(i);
            if (upgrade.type == Upgrade.SINGLE && upgrade.count > 0) {
                continue;
            }
            int rectX = x + i * 45;
            int rectY = y;

            // Check if click is inside this upgrade's box
            if (mouseX >= rectX && mouseX <= rectX + 40 &&
                    mouseY >= rectY && mouseY <= rectY + 40) {

                try {
                    upgrade.update();

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                break; // stop after handling one upgrade
            }

        }
    }

    public int getDPC() {
        return dpc * dpcMult;
    }

    public int getDPS() {
        return dps * dpsMult;
    }

}