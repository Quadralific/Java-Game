package entity;

import java.awt.*;

public abstract class Entity {
    public int x, y;

    public Entity(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public abstract void init(final Graphics graphics);
}