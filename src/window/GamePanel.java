package window;

import entity.Orb;
import entity.Player;
import utils.RandomNumbers;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public final class GamePanel extends JPanel implements Runnable {

    public static final int WINDOW_SIZE = 700;
    private final Player player;
    private final Orb orb;
    private int score = 0;
    private boolean running = false;
    private Thread thread;

    public GamePanel() {
        setPreferredSize(new Dimension(WINDOW_SIZE, WINDOW_SIZE));
        setOpaque(true);
        setFocusable(true);
        setBackground(Color.BLACK);
        addKeyListener(new KeyProcessor());

        player = new Player(250, 250);
        orb = new Orb(RandomNumbers.generate(WINDOW_SIZE), RandomNumbers.generate(WINDOW_SIZE));
        start();
    }

    private void start() {
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (!running) {
            return;
        }

        super.paintComponent(g);
        player.init(g);
        orb.init(g);

        g.setColor(Color.WHITE);
        g.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
        g.drawString("Score: " + score, (WINDOW_SIZE / 2) - 50, 20);
        g.dispose();
    }

    private void update() {
        final Rectangle rect = new Rectangle(player.x, player.y, 25, 25), rec2 = new Rectangle(orb.x, orb.y, 25, 25);
        if (rect.intersects(rec2)) {
            orb.x = RandomNumbers.generate(WINDOW_SIZE - 25);
            orb.y = RandomNumbers.generate(WINDOW_SIZE - 25);
            score++;
        }
    }

    @Override
    public void run() {
        while (running) {
            update();
            repaint();
        }
    }

    private final class KeyProcessor extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            super.keyPressed(e);
            final int stepPixel = 25;
            switch (e.getKeyCode()) {
                case KeyEvent.VK_W -> {
                    if (player.y - stepPixel >= 0) {
                        player.y -= stepPixel;
                    }
                }
                case KeyEvent.VK_A -> {
                    if (player.x - stepPixel >= 0) {
                        player.x -= stepPixel;
                    }
                }
                case KeyEvent.VK_S -> {
                    if (player.y + stepPixel <= WINDOW_SIZE - stepPixel) {
                        player.y += stepPixel;
                    }
                }
                case KeyEvent.VK_D -> {
                    if (player.x + stepPixel <= WINDOW_SIZE - stepPixel) {
                        player.x += stepPixel;
                    }
                }
            }
        }
    }
}