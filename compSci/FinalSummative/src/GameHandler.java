import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class GameHandler extends JPanel implements Runnable, KeyListener, MouseListener {
    private static JFrame window;
    private static boolean alive;
    private static GameHandler instance;
    private static long dopamineCount;
    private static Thread gameThread;
    private static UpgradeHandler upgradeHandler;
    private static List<FloatingNumberDisplay> numbers;
    private static JLayeredPane layeredPane;
    private static Image grass;
    private static long endTime;
    private static long startTime; // Start the stopwatch
    private static JButton clickButton; // Make button a field so we can access it

    /**
     * Singleton constructor to ensure only one instance of GameHandler exists.
     * Initializes the game window, button, and other components.
     */
    private GameHandler() {
        // Initialize the game state
        alive = false;
        instance = this;
        endTime = 0; // Reset end time
        startTime = 0; // Reset start time
        window = new JFrame("Dopamine Clicker"); // Creates the game window
        dopamineCount = 1000000;
        numbers = new CopyOnWriteArrayList<>();
        upgradeHandler = new UpgradeHandler(this, window); // Initialize UpgradeHandler - main upgrade management and related logic
        layeredPane = new JLayeredPane();
        grass = Toolkit.getDefaultToolkit().getImage("images/grass.gif"); // Load endgame image

        // Set up window
        window.setSize(1000, 700);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setLocationRelativeTo(null);

        // Set up the game panel (this)
        this.addKeyListener(this);
        this.setFocusable(true);
        this.addMouseListener(this);
        this.setOpaque(false); // Keep transparent so button shows through
        this.setBounds(0, 0, 1000, 700);

        // Create and set up the click button using JButton
        clickButton = new JButton("Click!");
        clickButton.addActionListener(e -> { // Handle button click
            Point loc = clickButton.getLocation();
            int dope = Math.random() * 100 < upgradeHandler.getCritUpgrade() ? upgradeHandler.getDPC() * 6
                    : upgradeHandler.getDPC();
            dopamineCount += dope;
            numbers.add(new FloatingNumber(loc.x + (int) (Math.random() * 50) + 5, loc.y, dope,
                    dope > upgradeHandler.getDPC()));
        });
        clickButton.setBounds((1000 - 100) / 2, (700 - 40) / 2 -30, 100, 40);
        clickButton.setVisible(true);

        // Set up layered pane
        layeredPane.setPreferredSize(new Dimension(1000, 700));
        layeredPane.setBounds(0, 0, 1000, 700);

        // Add components to layered pane - BUTTON ON TOP
        layeredPane.add(this, JLayeredPane.DEFAULT_LAYER); // Game panel on bottom
        layeredPane.add(clickButton, JLayeredPane.PALETTE_LAYER); // Button on top

        // Add mouse motion listener to the layered pane instead of in run()
        layeredPane.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseMoved(MouseEvent e) {
                upgradeHandler.hoverEvent(e, dopamineCount);
            }
        });
        

        // Set up window content
        window.setContentPane(layeredPane);
        window.setVisible(true);
    }

    /**
     * Get the singleton instance of GameHandler.
     * @return The singleton instance of GameHandler.
    */
    public static GameHandler getInstance() {
        if (instance == null) {
            instance = new GameHandler();
        }
        return instance;
    }

    /**
     * Paint the game components.
     * @param g The Graphics object used for painting.
     */
    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        // Create Graphics2D for better rendering
        Graphics2D g2d = (Graphics2D) g.create();
        // Clear the background
        g2d.setColor(getBackground());
        g2d.fillRect(0, 0, getWidth(), getHeight());
        if (upgradeHandler.isEndgame()) { // If endgame is reached, draw the endgame screen
            if (grass == null) {
                try {
                    grass = ImageIO.read(new File("images/grass.png")); // Load endgame image
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (endTime == 0) {
                endTime = System.currentTimeMillis(); // Record end time
            }
            g2d.drawImage(grass, 0, 0, getWidth(), getHeight(), this);
            g2d.setColor(Color.WHITE);

            g2d.fillRect(175, 290, 600, 95);
            g2d.setColor(Color.BLACK);
            g2d.setFont(new Font("Arial", Font.BOLD, 36));
            g2d.drawString(
                    "It took you " + (int) ((endTime - startTime) / 60000) + " minutes and ",
                    190, 330); // Display time in minutes
            g2d.drawString(
                    "" + ((endTime - startTime) / 1000.0 % 60) + " seconds to touch grass.",
                    190, 370); // Display seconds
            window.remove(clickButton); // Removes the click button

        } else { // If not endgame, draw the game normally
            // Draw game elements
            dopamineCount += upgradeHandler.drawBackground(g2d);

            for (FloatingNumberDisplay element : numbers) {
                element.draw(g2d); // Draws each floating number
            }

            // Draw UI text
            g2d.setColor(Color.BLACK);
            g2d.setFont(new Font("Arial", Font.BOLD, 18));
            g2d.drawString("Dopamine Count: " + dopamineCount, 20, 50);
            g2d.drawString("Dopamine Per Second: " + upgradeHandler.getDPS(), 20, 80);
            g2d.drawString("Dopamine Per Click: " + upgradeHandler.getDPC(), 20, 110);
            upgradeHandler.draw(g2d);
        }

        g2d.dispose(); // Clean up graphics context
    }

    /**
     * Start the game thread.
     */
    public synchronized void start() {
        if (alive)
            return;
        alive = true;
        gameThread = new Thread(this);
        gameThread.start();
    }

    /**
     * Stop the game thread.
     */
    public synchronized void stop() {
        alive = false;
        try {
            if (gameThread != null) {
                gameThread.join(); // Wait for the game thread to finish
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Run the game loop.
     */
    public void run() {
        Timer timer = new Timer();
        startTime = System.currentTimeMillis();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                dopamineCount += upgradeHandler.getDPS(); // Increment dopamine count by DPS every second
            }
        };

        long delay = 1000;
        long period = 1000;
        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseMoved(MouseEvent e) {
                upgradeHandler.hoverEvent(e, dopamineCount); // Handle mouse movement for upgrade tooltips
            }
        });
        timer.scheduleAtFixedRate(task, delay, period);

        while (alive) {
            update(); // Update game state
            repaint(); // Repaint the game panel

            try {
                Thread.sleep(16); // Sleep for 16ms ~60 FPS
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        timer.cancel(); // Clean up timer
        stop(); // Stop the game thread
    }
    /**
     * Update the game state.
     */
    private void update() {
        numbers.removeIf(d -> {
            d.update();
            return !d.isAlive();
        });
    }

    public void keyPressed(KeyEvent e) {}

    public void keyReleased(KeyEvent e) {}

    public void keyTyped(KeyEvent e) {}


    @Override
    public void mouseClicked(MouseEvent e) {
        dopamineCount -= upgradeHandler.mouseEvent(e, dopamineCount); // Handle mouse click events. Subtracts the price of the upgrade if purchased
    }

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}
}