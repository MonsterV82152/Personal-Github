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
    private List<JButton> buttons;
    
    public UpgradeHandler() {
        upgrades = new ArrayList<>();
        upgrades.add(new Upgrade("Upgrade Clicker", 10, 1.5));
        upgrades.add(new Upgrade("Hydraulic Press", 100, 1.5));
        upgrades.add(new Upgrade("Subway Surfers", 200));
        upgrades.add(new Upgrade("Lofi Beats", 300));
    }
    public void draw(Graphics g) {

    }
    public void update(MouseEvent e) {
        
    }


}