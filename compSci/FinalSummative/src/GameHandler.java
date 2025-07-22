import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.LayoutStyle;
import javax.swing.plaf.DimensionUIResource;
import javax.swing.plaf.TextUI;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GameHandler extends Canvas implements Runnable, KeyListener, MouseListener {
    private static JFrame window;
    private static boolean alive;
    private static GameHandler instance;
    private static long dopamineCount;
    private static Thread gameThread;
    private static UpgradeHandler upgradeHandler;
    private static List<FloatingNumberDisplay> numbers;

    private GameHandler() {
        window = new JFrame("Dopamine Clicker");
        
        dopamineCount = 0;
        numbers = new ArrayList<>();
        upgradeHandler = new UpgradeHandler();

        


        window.setSize(1000, 700);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setLayout(null);
        this.addKeyListener(this);
        this.setFocusable(true);
        this.setSize(window.getSize());

        JButton button = new JButton("Click!");
        button.addActionListener(e -> {
            Point loc = button.getLocation();
            dopamineCount += upgradeHandler.getDPC();
            numbers.add(new FloatingNumber(loc.x + (int)(Math.random() * 50)+5, loc.y, upgradeHandler.getDPC()));
        });
        button.setSize(100, 40);
        button.setLocation((1000 - 100) / 2, (700 - 40) / 2);
        window.add(button);
        window.add(this);

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);

    }

    public static GameHandler getInstance() {
        if (instance == null) {
            instance = new GameHandler();
        }
        return instance;
    }

    @Override
    public void paint(Graphics g) {
        // g.setColor(Color.WHITE);
        // g.fillRect(0, 0, getWidth(), getHeight());
        numbers.forEach(element -> {
            element.draw(g);
        });
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 18));
        g.drawString("Dopamine Count: " + dopamineCount, 20, 30);
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
            gameThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void run() {

        while (alive) {
            numbers.removeIf(d -> {
                d.update();
                return !d.isAlive();
            });
            repaint();

            try {
                Thread.sleep(16); // ~60 FPS
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        stop();

    }

    public void keyPressed(KeyEvent e) {

    }

    public void keyReleased(KeyEvent e) {

    }

    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        upgradeHandler.update(e);
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
