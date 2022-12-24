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
    private final int WINDOW_SIZE = 700;
    private int score = 0;
    private boolean running = false;
    private Thread thread;
    private Player player;
    private Orb orb;

    public GamePanel() {
        setPreferredSize(new Dimension(WINDOW_SIZE, WINDOW_SIZE));
        setOpaque(true);
        setFocusable(true);
        setBackground(Color.BLACK);
        addKeyListener(new KeyProcessor());

        player = new Player(250, 250);
        orb = new Orb(RandomNumbers.generate(1000), RandomNumbers.generate(1000));
        start();
    }

    private void start() {
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        if(!running) return;

        super.paintComponent(g);
        player.init(g);
        orb.init(g);

        g.setColor(Color.WHITE);
        g.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
        g.drawString("Score: " + score, 250, 20);
        g.dispose();
    }

    private void update() {
        final Rectangle rect = new Rectangle(player.x, player.y, 25, 25),
                        rec2 = new Rectangle(orb.x, orb.y, 25, 25);
        if(rect.intersects(rec2)) {
            orb.x = RandomNumbers.generate(800);
            orb.y = RandomNumbers.generate(800);
            score++;
        }
    }

    @Override
    public void run() {
        while(running) {
            update();
            repaint();
        }
    }

    private final class KeyProcessor extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            super.keyPressed(e);
            switch(e.getKeyCode()) {
                case KeyEvent.VK_W -> player.y -= 25;
                case KeyEvent.VK_A -> player.x -= 25;
                case KeyEvent.VK_S -> player.y += 25;
                case KeyEvent.VK_D -> player.x += 25;
            }
        }
    }
}