import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JButton;
import javax.swing.JFrame;

public class UpgradeHandler {
    private int dps;
    private int dpsMult;
    private int dpc;
    private int dpcMult;
    private Canvas parentCanvas;
    private JFrame window;
    private List<Upgrade> upgrades;
    private List<GifSprite> sprites;
    private Tooltip tooltip = null;
    private int x = 413, y = 380; // Default position for upgrades

    public UpgradeHandler(Canvas parentCanvas, JFrame window) {
        this.parentCanvas = parentCanvas;
        this.window = window;
        dpc = 1;
        dpcMult = 1;
        dps = 0;
        dpsMult = 1;
        sprites = new CopyOnWriteArrayList<>();
        upgrades = new CopyOnWriteArrayList<>();
        upgrades.add(new Upgrade("Upgrade Clicker", 10, 1.5,
                "images/clickerIcon.png", () -> {
                    dpc++;
                    return null;
                }));
        upgrades.add(new Upgrade("Hydraulic Press", 100, 1.5,
                "images/hydraulicPress.png", () -> {
                    dps += 20;
                    GifSprite sprite = new GifSprite("images/hydraulic_press.gif", 800, 500, 200, 200);
                    if (!sprites.contains(sprite)) {
                        sprites.add(sprite);
                    }
                    return null;
                }));
        upgrades.add(new Upgrade("Subway Surfers", 500, 1.5, "images/subwaySurfer.png", () -> {
            dps += 100;
            GifSprite sprite = new GifSprite("images/subway_surfer.gif", 600, 500, 200, 200);
            if (!sprites.contains(sprite)) {
                sprites.add(sprite);
            }
            return null;
        }));
        upgrades.add(new Upgrade("Lofi Beats", 700, 1.5, "images/lofiBeats.png", () -> {
            dps += 200;
            GifSprite sprite = new GifSprite("images/lofi-girl.gif", 400, 500, 200, 200);
            if (!sprites.contains(sprite)) {
                sprites.add(sprite);
                File audioFile = new File("sounds/lofi_beats.wav");
                AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);

                // Get clip and open
                Clip clip = AudioSystem.getClip();
                clip.open(audioStream);
                clip.loop(Clip.LOOP_CONTINUOUSLY); // or clip.loop(Clip.LOOP_CONTINUOUSLY);
            }
            return null;

        }));
    }

    public void draw(Graphics g) {
        int count = 0;
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
        if (tooltip != null) {
            tooltip.draw(g);
        }
        for (GifSprite sprite : sprites) {
            g.drawImage(sprite.image, sprite.x, sprite.y, sprite.width, sprite.height, parentCanvas);
        }
    }

    public int mouseEvent(MouseEvent e, long dopamineCount) {
        int mouseX = e.getX();
        int mouseY = e.getY();
        int price = 0;

        System.out.println("Click at: " + mouseX + ", " + mouseY);

        for (int i = -1, counter = 0; counter < upgrades.size(); counter++) {
            Upgrade upgrade = upgrades.get(counter);
            if (upgrade.type == Upgrade.SINGLE && upgrade.count > 0) {
                System.out.println("Skipping upgrade " + upgrade.name + " as it is already purchased.");
                continue;
            }
            i++;
            int rectX = x + i * 45;
            int rectY = y;

            // Check if click is inside this upgrade's box
            if (mouseX >= rectX && mouseX <= rectX + 40 &&
                    mouseY >= rectY && mouseY <= rectY + 40) {

                try {
                    if (dopamineCount >= upgrade.price) {
                        price = upgrade.price;
                        upgrade.update();
                        hoverEvent(e);
                    } else {
                        System.out.println("Not enough dopamine to buy " + upgrade.name);
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                break; // stop after handling one upgrade
            }

        }
        return price;
    }

    public void hoverEvent(MouseEvent e) {
        int mouseX = e.getX();
        int mouseY = e.getY();

        tooltip = null; // Clear tooltip

        for (int i = -1, counter = 0; counter < upgrades.size(); counter++) {
            Upgrade upgrade = upgrades.get(counter);
            if (upgrade.type == Upgrade.SINGLE && upgrade.count > 0)
                continue;
            i++;
            int rectX = x + i * 45;
            int rectY = y;

            if (mouseX >= rectX && mouseX <= rectX + 40 &&
                    mouseY >= rectY && mouseY <= rectY + 40) {
                tooltip = new Tooltip(upgrade.name, "Price: " + upgrade.price, mouseX + 10, mouseY);
                break;
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