package entity;

import java.awt.*;

public final class Player extends Entity {

    public Player(int x, int y) {
        super(x, y);
    }

    @Override
    public void init(Graphics graphics) {
        graphics.setColor(Color.GREEN);
        graphics.fillRect(x, y, 25, 25);
    }
}