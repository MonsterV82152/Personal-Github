import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;

public class UpgradeHandler{
    private int dps;
    private int dpsMult;
    private int dpc;
    private int dpcMult;
    private List<Upgrade> upgrades;
    private List<GifSprite> sprites;
    
    public UpgradeHandler() {
        dpc = 1;
        dpcMult = 1;
        dps = 0;
        dpsMult = 1;
        upgrades = new ArrayList<>();
        upgrades.add(new Upgrade("Upgrade Clicker", 10, 1.5, new Rectangle(0, 0, 100, 100), () -> {
            dpc++;
            return null;
        }));
        upgrades.add(new Upgrade("Hydraulic Press", 100, 1.5, new Rectangle(0, 0, 100, 100), () -> {
            dps+=100;
            if (sprites.contains(new GifSprite(null, 0, 0))) {
                sprites.remove(new GifSprite(null, 0, 0));
            }
            return null;
        }));
        upgrades.add(new Upgrade("Subway Surfers", 200, new Rectangle(0, 0, 100, 100), () -> {
            dps+=200;
            return null;
        }));
        upgrades.add(new Upgrade("Lofi Beats", 300, new Rectangle(0, 0, 100, 100), () -> {
            dps+=300;
            return null;
        }));
    }
    public void draw(Graphics g) {

    }
    public void update(MouseEvent e) {
        
    }
    public int getDPC() {
        return dpc*dpcMult;
    }
    public int getDPS() {
        return dps*dpsMult;
    }
    
}