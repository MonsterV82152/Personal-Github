import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class UpgradeHandler {
    private int dps;
    private int dpsMult;
    private int dpc;
    private int dpcMult;
    private JPanel parentCanvas;
    private JFrame window;
    private boolean endgame;
    private List<Upgrade> upgrades;
    private List<GifSprite> sprites;
    private Tooltip tooltip = null;
    private int x = 400, y = 380; // Default position for upgrades
    private List<DVDHandler> dvds;
    private int critupgrade = 0;

    public UpgradeHandler(JPanel parentCanvas, JFrame window) {
        this.parentCanvas = parentCanvas;
        this.window = window;
        dpc = 1;
        dpcMult = 1;
        dps = 0;
        endgame = false;
        dpsMult = 1;
        sprites = new CopyOnWriteArrayList<>();
        upgrades = new CopyOnWriteArrayList<>();
        dvds = new CopyOnWriteArrayList<>();
        upgrades.add(new Upgrade("Upgrade Clicker", 10, 1.5,
                "images/clickerIcon.png", () -> {
                    dpc++;
                    return null;
                }));
        upgrades.add(new Upgrade("DVDs", 10, 2, "images/dvd.png", () -> {
            dvds.add(new DVDHandler());
            return null;

        }));
        upgrades.add(new Upgrade("Hydraulic Press", 100, 1.5,
                "images/hydraulicPress.png", () -> {
                    dps += 10;
                    GifSprite sprite = new GifSprite("images/hydraulic_press.gif", 800, 500, 200, 200);
                    if (!sprites.contains(sprite)) {
                        sprites.add(sprite);
                    }
                    return null;
                }));
        upgrades.add(new Upgrade("Lofi Beats", 500, "images/lofiBeats.png", () -> {
            dps += 25;
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
        upgrades.add(new Upgrade("Critical Hit", 600, "images/crit.png", () -> {
            critupgrade = 5;
            return null;
        }));
        upgrades.add(new Upgrade("Breaking News", 700, "images/breaking_news.png", () -> {
            dps += 50;
            GifSprite sprite = new GifSprite("images/breakingNews.gif", 0, 0, 1000, 20);
            if (!sprites.contains(sprite)) {
                sprites.add(sprite);
            }
            return null;
        }));
        upgrades.add(new Upgrade("Subway Surfers", 1000, "images/subwaySurfer.png", () -> {
            dps += 100;
            GifSprite sprite = new GifSprite("images/subway_surfer.gif", 600, 500, 200, 200);
            if (!sprites.contains(sprite)) {
                sprites.add(sprite);
            }
            return null;
        }));
        upgrades.add(new Upgrade("Soothing Rain Sounds", 2500, "images/rain.png", () -> {
            dps += 150;

            File audioFile = new File("sounds/rain.wav");
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);

            // Get clip and open
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);

            return null;
        }));
        upgrades.add(new Upgrade("Upgrade Critical Hit", 7000, "images/crit.png", () -> {
            critupgrade = 15;
            return null;
        }));
        upgrades.add(new Upgrade("Touch Grass", 20000, "images/grass.png", () -> {
            endgame = true;
            return null;
        }));
    }

    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        int visibleCount = 0;
        int maxVisible = 5;

        int totalVisible = 0;
        for (Upgrade element : upgrades) {
            if (element.type == Upgrade.SINGLE && element.count > 0) continue;
            totalVisible++;
        }
        totalVisible = Math.min(totalVisible, maxVisible);

        for (Upgrade element : upgrades) {
            if (element.type == Upgrade.SINGLE && element.count > 0) continue;
            if (visibleCount >= maxVisible) break;

            element.targetX = x + visibleCount * 45;

            if (visibleCount == totalVisible - 1) {
                element.drawX = element.targetX;
                element.alpha = Math.min(1.0f, element.alpha + 0.01f);
            } else {
                if (Math.abs(element.drawX - element.targetX) > 1000) {
                    // First time showing — place directly
                    element.drawX = element.targetX;
                } else {
                    // Already visible — slide normally
                    if (element.drawX < element.targetX) {
                        element.drawX += Math.min(3, element.targetX - element.drawX);
                    } else if (element.drawX > element.targetX) {
                        element.drawX -= Math.min(3, element.drawX - element.targetX);
                    }
                }
                element.alpha = 1.0f;
            }

            if (element.alpha > 0.01f) {
                g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, element.alpha));
                g2d.setColor(Color.cyan);
                g2d.drawRect(element.drawX, y, 40, 40);
                g2d.drawImage(element.image, element.drawX + 1, y + 1, 39, 39, parentCanvas);
            }

            visibleCount++;
        }

        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));

        if (tooltip != null) {
            tooltip.draw(g);
        }
    }



    public int drawBackground(Graphics g) {
        int sum = 0;
        for (DVDHandler dvd : dvds) {
            sum += dvd.update();
            dvd.draw(g, parentCanvas);

        }
        for (GifSprite sprite : sprites) {
            g.drawImage(sprite.image, sprite.x, sprite.y, sprite.width, sprite.height, parentCanvas);
        }
        return sum;

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
                        hoverEvent(e, dopamineCount);
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

    public void hoverEvent(MouseEvent e, long dopamine) {
        int mouseX = e.getX();
        int mouseY = e.getY();

        tooltip = null; // Clear tooltip

        for (int i = -1, counter = 0; counter < upgrades.size(); counter++) {
            Upgrade upgrade = upgrades.get(counter);
            if (upgrade.type == Upgrade.SINGLE && upgrade.count > 0)
                continue;
            if (i >= 4) {
                break; // Limit to first 5 upgrades
            }
            i++;
            int rectX = x + i * 45;
            int rectY = y;

            if (mouseX >= rectX && mouseX <= rectX + 40 &&
                    mouseY >= rectY && mouseY <= rectY + 40) {
                tooltip = new Tooltip(upgrade.name, "Price: " + upgrade.price, mouseX + 10, mouseY,
                        upgrade.price > dopamine);
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

    public int getCritUpgrade() {
        return critupgrade;
    }
    public boolean isEndgame() {
        return endgame;
    }

}