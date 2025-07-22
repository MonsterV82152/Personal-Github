import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CopyOnWriteArrayList;

public class GameHandler extends Canvas implements Runnable, KeyListener, MouseListener {
    private static JFrame window;
    private static boolean alive;
    private static GameHandler instance;
    private static long dopamineCount;
    private static Thread gameThread;
    private static UpgradeHandler upgradeHandler;
    private static List<FloatingNumberDisplay> numbers;
    private BufferStrategy bs;

    private GameHandler() {
        window = new JFrame("Dopamine Clicker");

        dopamineCount = 0;
        numbers = new CopyOnWriteArrayList<>();
        upgradeHandler = new UpgradeHandler(this, window);

        window.setSize(1000, 700);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setLayout(null);
        this.addKeyListener(this);
        this.setFocusable(true);
        this.addMouseListener(this);
        this.setIgnoreRepaint(true);
        window.setLocationRelativeTo(null);

        this.setSize(window.getSize());

        JButton button = new JButton("Click!");
        button.addActionListener(e -> {
            Point loc = button.getLocation();
            dopamineCount += upgradeHandler.getDPC();
            numbers.add(new FloatingNumber(loc.x + (int) (Math.random() * 50) + 5, loc.y, upgradeHandler.getDPC()));
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

    private void render() {
        bs = getBufferStrategy();
        if (bs == null)
            return;

        do {
            do {
                Graphics g = bs.getDrawGraphics();
                try {
                    g.clearRect(0, 0, 1000, 700); // Clear old frame
                    dopamineCount += upgradeHandler.drawBackground(g);
                    for (FloatingNumberDisplay element : numbers) {
                        element.draw(g);
                    }
                    g.setColor(Color.BLACK);
                    g.setFont(new Font("Arial", Font.BOLD, 18));
                    g.drawString("Dopamine Count: " + dopamineCount, 20, 30);
                    g.drawString("Dopamine Per Second: " + upgradeHandler.getDPS(), 20, 60);
                    g.drawString("Dopamine Per Click: " + upgradeHandler.getDPC(), 20, 90);
                    upgradeHandler.draw(g);

                } finally {
                    g.dispose();
                }

            } while (bs.contentsRestored());
            bs.show();
        } while (bs.contentsLost());
    }

    public synchronized void start() {
        this.createBufferStrategy(2);
        bs = getBufferStrategy();
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

        Timer timer = new Timer();

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
                upgradeHandler.hoverEvent(e);
            }
        });

        timer.scheduleAtFixedRate(task, delay, period);
        while (alive) {
            update();
            render();

            try {
                Thread.sleep(16); // ~60 FPS
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        stop();

    }

    private void update() {
        numbers.removeIf(d -> {
            d.update();
            return !d.isAlive();
        });
    }

    public void keyPressed(KeyEvent e) {

    }

    public void keyReleased(KeyEvent e) {

    }

    public void keyTyped(KeyEvent e) {
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
