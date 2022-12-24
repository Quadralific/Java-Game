package window;

import javax.swing.*;

public final class Window extends JFrame {

    public Window(final String name) {
        super(name);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        add(new GamePanel());
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
