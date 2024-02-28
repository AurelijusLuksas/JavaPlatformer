package main;

import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * @author Aurelijus Lukšas 5 group.
 * Class of the entity.
 * Can be used by entities which need hitbox.
 */
public abstract class Entity {
    Rectangle2D.Float hitbox;
    protected float x, y;
    protected int width, height;

    /**
     * Constructor used for getting entities position and size.
     * @param x
     * @param y
     * @param width
     * @param height
     */
    public Entity(float x, float y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

    }

    /**
     * Method used for debugging by drawing a hitbox around the entity.
     * @param g
     * @param levelOffsetX
     */
    void drawHitbox(Graphics g, int levelOffsetX) {
        // For Debugging
        g.setColor(Color.red);
        g.drawRect((int)(hitbox.x) - levelOffsetX, (int)(hitbox.y), (int)hitbox.width, (int)hitbox.height);
    }

    /**
     * This method creates the hitbox of the entity.
     * @param x
     * @param y
     * @param width
     * @param height
     */
    protected void createHitbox(float x, float y, float width, float height) {
        hitbox = new Rectangle2D.Float(x, y, width, height);

    }

    /**
     * Hitbox getter.
     * @return
     */
    public Rectangle2D.Float getHitbox() {
        return hitbox;
    }

}
