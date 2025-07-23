import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
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

    private GameHandler() {
        window = new JFrame("Dopamine Clicker");
        dopamineCount = 100000;
        numbers = new CopyOnWriteArrayList<>();
        upgradeHandler = new UpgradeHandler(this, window);
        layeredPane = new JLayeredPane();

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

        // Create and set up the click button
        clickButton = new JButton("Click!");
        clickButton.addActionListener(e -> {
            Point loc = clickButton.getLocation();
            int dope = Math.random() * 100 < upgradeHandler.getCritUpgrade() ? upgradeHandler.getDPC() * 6
                    : upgradeHandler.getDPC();
            dopamineCount += dope;
            numbers.add(new FloatingNumber(loc.x + (int) (Math.random() * 50) + 5, loc.y, dope,
                    dope > upgradeHandler.getDPC()));
        });
        clickButton.setBounds((1000 - 100) / 2, (700 - 40) / 2, 100, 40);
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
        grass = Toolkit.getDefaultToolkit().getImage("images/grass.gif");

        // Set up window content
        window.setContentPane(layeredPane);
        window.setVisible(true);
    }

    public static GameHandler getInstance() {
        if (instance == null) {
            instance = new GameHandler();
        }
        return instance;
    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        // Create Graphics2D for better rendering
        Graphics2D g2d = (Graphics2D) g.create();
        // Clear the background
        g2d.setColor(getBackground());
        g2d.fillRect(0, 0, getWidth(), getHeight());
        if (upgradeHandler.isEndgame()) {
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
                    190, 330);
            g2d.drawString(
                    "" + ((endTime - startTime) / 1000.0 % 60) + " seconds to touch grass.",
                    190, 370);
            window.remove(clickButton);

        } else {
            // Draw game elements
            dopamineCount += upgradeHandler.drawBackground(g2d);

            for (FloatingNumberDisplay element : numbers) {
                element.draw(g2d);
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

    public synchronized void start() {
        if (alive)
            return;
        alive = true;
        gameThread = new Thread(this);
        gameThread.start();
    }

    public synchronized void stop() {
        alive = false;
        try {
            if (gameThread != null) {
                gameThread.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        Timer timer = new Timer();
        startTime = System.currentTimeMillis();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                dopamineCount += upgradeHandler.getDPS();
            }
        };

        long delay = 1000;
        long period = 1000;
        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseMoved(MouseEvent e) {
                upgradeHandler.hoverEvent(e, dopamineCount);
            }
        });
        timer.scheduleAtFixedRate(task, delay, period);

        while (alive) {
            update();
            repaint();

            try {
                Thread.sleep(16); // ~60 FPS
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        timer.cancel(); // Clean up timer
        stop();
    }

    private void update() {
        numbers.removeIf(d -> {
            d.update();
            return !d.isAlive();
        });
    }

    public void keyPressed(KeyEvent e) {
        // Add key handling if needed
    }

    public void keyReleased(KeyEvent e) {
        // Add key handling if needed
    }

    public void keyTyped(KeyEvent e) {
        // Add key handling if needed
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        dopamineCount -= upgradeHandler.mouseEvent(e, dopamineCount);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }
}