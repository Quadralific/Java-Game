package entity;

import java.awt.*;

public final class Orb extends Entity {

    public Orb(int x, int y) {
        super(x, y);
    }

    @Override
    public void init(Graphics graphics) {
        graphics.setColor(Color.BLUE);
        graphics.fillOval(x, y, 25, 25);
    }
}