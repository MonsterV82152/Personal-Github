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


/**
 * UpgradeHandler manages the upgrades in the game, including their effects, drawing them on the screen,
 */
public class UpgradeHandler {
    private int dps; // Dopamine per second
    private int dpsMult; // Dopamine per second multiplier
    private int dpc; // Dopamine per click
    private int dpcMult; // Dopamine per click multiplier
    private JPanel parentCanvas;
    private JFrame window;
    private boolean endgame;
    private List<Upgrade> upgrades; // List of available upgrades
    private List<GifSprite> sprites; // List of GIF sprites for visual effects
    private Tooltip tooltip = null; // Tooltip for displaying upgrade information
    private int x = 330, y = 380; // Default position for upgrades
    private List<DVDHandler> dvds; // List of DVD handlers for managing DVD upgrades
    private int critupgrade = 0; // Critical upgrade level

    /**
     * Constructor initializes the UpgradeHandler with the parent canvas and window.
     * Sets up initial upgrades and their actions.
     *
     * @param parentCanvas The parent JPanel for drawing upgrades
     * @param window       The main JFrame of the game
     */
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
        // Adds initial upgrades with their actions and prices
        upgrades.add(new Upgrade("Upgrade Clicker", 10, 1.5, // Upgrade dopamine per click
                "images/clickerIcon.png", () -> {
                    dpc++;
                    return null;
                }));
        upgrades.add(new Upgrade("DVDs", 10, 2, "images/dvd.png", () -> { // Adds a new DVD handler
            dvds.add(new DVDHandler());
            return null;

        }));
        upgrades.add(new Upgrade("Hydraulic Press", 100, 1.5, // Upgrade dopamine per second
                "images/hydraulicPress.png", () -> {
                    dps += 10;
                    GifSprite sprite = new GifSprite("images/hydraulic_press.gif", 800, 500, 200, 200);
                    if (!sprites.contains(sprite)) {
                        sprites.add(sprite);
                    }
                    return null;
                }));
        upgrades.add(new Upgrade("Lofi Beats", 500, "images/lofiBeats.png", () -> { // Upgrade dopamine per second
            dps += 25;
            GifSprite sprite = new GifSprite("images/lofi-girl.gif", 300, 500, 200, 200);
            if (!sprites.contains(sprite)) {
                sprites.add(sprite);
                File audioFile = new File("sounds/lofi_beats.wav");
                AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);

                // Get clip and open
                Clip clip = AudioSystem.getClip();
                clip.open(audioStream);
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            }
            return null;
        }));
        upgrades.add(new Upgrade("Critical Hit", 600, "images/crit.png", () -> { // Upgrade critical hit chance
            critupgrade = 5;
            return null;
        }));
        upgrades.add(new Upgrade("Breaking News", 700, "images/breaking_news.png", () -> { // Upgrade dopamine per second
            dps += 50;
            GifSprite sprite = new GifSprite("images/breakingNews.gif", 0, 0, 1000, 20);
            if (!sprites.contains(sprite)) {
                sprites.add(sprite);
            }
            return null;
        }));
        upgrades.add(new Upgrade("Subway Surfers", 1000, "images/subwaySurfer.png", () -> { // Upgrade dopamine per second
            dps += 100;
            GifSprite sprite = new GifSprite("images/subway_surfer.gif", 500, 500, 300, 200);
            if (!sprites.contains(sprite)) {
                sprites.add(sprite);
            }
            return null;
        }));
        upgrades.add(new Upgrade("Soothing Rain Sounds", 2500, "images/rain.png", () -> { // Upgrade dopamine per second
            dps += 150;

            File audioFile = new File("sounds/rain.wav");
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);

            // Get clip and open
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);

            return null;
        }));
        upgrades.add(new Upgrade("Upgrade Critical Hit", 7000, "images/crit.png", () -> { // Upgrade critical hit chance
            critupgrade = 15;
            return null;
        }));
        upgrades.add(new Upgrade("ASMR Slime Videos", 1000, "images/subwaySurfer.png", () -> { // Upgrade dopamine per second
            dps += 100;
            GifSprite sprite = new GifSprite("images/asmr_slime.gif", 0, 50, 300, 200);
            if (!sprites.contains(sprite)) {
                sprites.add(sprite);
            }
            return null;
        }));
        upgrades.add(new Upgrade("Watch a Guy Eat", 1000, "images/subwaySurfer.png", () -> { // Upgrade dopamine per second
            dps += 100;
            GifSprite sprite = new GifSprite("images/guy_eating.gif", 0, 300, 300, 200);
            if (!sprites.contains(sprite)) {
                sprites.add(sprite);
            }
            return null;
        }));
        upgrades.add(new Upgrade("Touch Grass", 20000, "images/grass.png", () -> { // End the game
            endgame = true;
            return null;
        }));
    }

    /**
     * Draws the upgrades and their tooltips on the screen.
     *
     * @param g The Graphics object used for drawing
     */
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g; // Use Graphics2D for better rendering

        // Draw upgrade icons - each upgrade is drawn in a grid-like fashion and new upgrades slide in
        int visibleCount = 0; // Count how many upgrades are visible
        int maxVisible = 5;

        int totalVisible = 0;
        for (Upgrade element : upgrades) {
            if (element.type == Upgrade.SINGLE && element.count > 0) continue;
            totalVisible++;
        }
        totalVisible = Math.min(totalVisible, maxVisible); // Limit to maxVisible upgrades

        for (Upgrade element : upgrades) {
            if (element.type == Upgrade.SINGLE && element.count > 0) continue;
            if (visibleCount >= maxVisible) break;

            element.targetX = x + visibleCount * 70;

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
                element.alpha = 1.0f; // Fully visible after sliding
            }

            if (element.alpha > 0.01f) { // Only draw if the upgrade is visible enough
                g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, element.alpha));
                g2d.setColor(Color.cyan);
                g2d.drawRect(element.drawX, y, 60, 60);
                g2d.drawImage(element.image, element.drawX + 1, y + 1, 59, 59, parentCanvas);
            }

            visibleCount++; // Increment visible count for next upgrade
        }

        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));

        if (tooltip != null) {
            tooltip.draw(g); // Draw the tooltip if it exists (e.g., when hovering over an upgrade)
        }
    }


    /**
     * Draws the background for the upgrades, including DVD handlers and GIF sprites.
     *
     * @param g The Graphics object used for drawing
     * @return The total dopamine count from all DVD handlers
     */
    public int drawBackground(Graphics g) {
        int sum = 0;
        for (DVDHandler dvd : dvds) { // Update and draw each DVD handler
            sum += dvd.update();
            dvd.draw(g, parentCanvas);
        }
        for (GifSprite sprite : sprites) { // Draw each GIF sprite
            g.drawImage(sprite.image, sprite.x, sprite.y, sprite.width, sprite.height, parentCanvas);
        }
        return sum; // Return the total dopamine gain from all DVD handlers

    }
    /**
     * Handles mouse click events to purchase upgrades.
     * @param e The MouseEvent containing mouse position
     * @param dopamineCount The current dopamine count
     * @return The price of the upgrade purchased, or 0 if none was purchased
     */
    public int mouseEvent(MouseEvent e, long dopamineCount) { 
        int mouseX = e.getX(); // Get mouse X position
        int mouseY = e.getY(); // Get mouse Y position
        int price = 0;

        System.out.println("Click at: " + mouseX + ", " + mouseY);

        for (int i = -1, counter = 0; counter < upgrades.size(); counter++) { // Iterate through upgrades
            Upgrade upgrade = upgrades.get(counter); 
            if (upgrade.type == Upgrade.SINGLE && upgrade.count > 0) {
                System.out.println("Skipping upgrade " + upgrade.name + " as it is already purchased.");
                continue; // Skip already purchased upgrades
            }
            i++; // Increment index for grid-like layout
            int rectX = x + i * 70; // Calculate the X position for this upgrade
            int rectY = y; // Y position is constant for all upgrades

            // Check if click is inside this upgrade's box
            if (mouseX >= rectX && mouseX <= rectX + 60 &&
                    mouseY >= rectY && mouseY <= rectY + 60) {

                try {
                    if (dopamineCount >= upgrade.price) { // Check if enough dopamine to purchase upgrade
                        price = upgrade.price;
                        upgrade.update(); // Update the upgrade (e.g., apply its effect and increment count)
                        hoverEvent(e, dopamineCount); // Show tooltip for the upgraded item
                    } else {
                        System.out.println("Not enough dopamine to buy " + upgrade.name); 
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                break; // stop after handling one upgrade
            }

        }
        return price; // Return the price of the upgrade purchased, or 0 if none was purchased
    }

    /**
     * Handles mouse hover events to show tooltips for upgrades.
     *
     * @param e        The MouseEvent containing mouse position
     * @param dopamine The current dopamine count
     */
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
            int rectX = x + i * 70;
            int rectY = y;

            if (mouseX >= rectX && mouseX <= rectX + 60 &&
                    mouseY >= rectY && mouseY <= rectY + 60) {
                tooltip = new Tooltip(upgrade.name, "Price: " + upgrade.price, mouseX + 10, mouseY, // Create tooltip with upgrade name and price
                        upgrade.price > dopamine); // Show red if not enough dopamine
                break;
            }
        }
    }

    /**
     * Returns the total dopamine per click.
     * @return The total dopamine per click
     */
    public int getDPC() {
        return dpc * dpcMult;
    }

    /**
     * Returns the total dopamine per second.
     * @return The total dopamine per second
     */
    public int getDPS() {
        return dps * dpsMult;
    }

    /**
     * Returns the current critical chance.
     * @return The current critical chance
     */
    public int getCritUpgrade() {
        return critupgrade;
    }
    /**
     * Returns whether the game has reached the endgame state.
     * @return True if the endgame condition is met, false otherwise
     */
    public boolean isEndgame() {
        return endgame;
    }

}