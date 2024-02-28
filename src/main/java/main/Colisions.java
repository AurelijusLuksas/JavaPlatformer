package main;
import States.Play;

import java.awt.geom.Rectangle2D;

import static main.Data.Display.*;

/**
 * @author Aurelijus Lukšas 5 group.
 * Class used for calculating collisions.
 */
public class Colisions {
    public static int currentLVLIndex = 1;
    public static boolean canChangeLVL = true;

    /**
     * Calculates if a player entity can move.
     * @param x
     * @param y
     * @param width
     * @param height
     * @param levelData
     * @return
     */
    public static boolean canMove(float x, float y, float width, float height, int[][] levelData) {
        if (!isSolid(x, y, levelData))
            if (!isSolid(x + width, y + height, levelData))
                if (!isSolid(x + width, y, levelData))
                    if (!isSolid(x, y + height, levelData))
                        return true;
        return false;
    }

    /**
     * Checks if the tile of the player or below him is solid.
     * Also checks for level start and level end tiles.
     * @param x
     * @param y
     * @param levelData
     * @return
     */
    private static boolean isSolid(float x, float y, int[][] levelData) {
        if (x < 0 || x >= MapWidth * TilesWidth) {
            return true;
        }
        if (y < 0 || y >= MapHeight * TilesWidth) {
            return true;
        }
        float xIndex = x / TilesWidth;
        float yIndex = y / TilesWidth;
        int value = levelData[(int)yIndex][(int)xIndex];

        if(value != 0 && value != 59 && value != 45){
            return true;
        }
        if (value == 59)
            canChangeLVL = true;

        if (value == 45) {
            if (canChangeLVL) {
                currentLVLIndex++;
                canChangeLVL = false;
            }
            LevelHandler.currentLevel = "level" + currentLVLIndex + ".json";
            LevelHandler.loadMap();
            Play.getMapData();
        }

        return false;
    }

    /**
     * Gets the collision tile on the X axis.
     * @param hitbox
     * @param xSpeed
     * @return
     */
    public static float GetXCollision(Rectangle2D.Float hitbox, float xSpeed) {
        int tile = (int)(hitbox.x / TilesWidth);
        if(xSpeed > 0) {
            int tileXPos = tile * TilesWidth;
            int xOffset = (int)(TilesWidth - hitbox.width);
            return tileXPos + xOffset - 1;
        }else {
            return tile * TilesWidth;
        }
    }

    /**
     * Gets the collision tile on the Y axis.
     * @param hitbox
     * @param airSpeed
     * @return
     */
    public static float GetYPositionCollision(Rectangle2D.Float hitbox, float airSpeed) {
        int tile = (int)(hitbox.y / TilesWidth);
        if(airSpeed > 0) {
            int tileYPos = tile * TilesWidth;
            int yOffset = (int)(TilesWidth - hitbox.height);
            return tileYPos + yOffset - 1;
        }else {
            return tile * TilesWidth;
        }
    }

    /**
     * Checks if the player entity is on the ground.
     * @param hitbox
     * @param levelData
     * @return
     */
    public static boolean EntityOnGround(Rectangle2D.Float hitbox, int[][] levelData) {
        if(!isSolid(hitbox.x, hitbox.y + hitbox.height + 1, levelData))
            if(!isSolid(hitbox.x + hitbox.width, hitbox.y + hitbox.height + 1, levelData))
                return false;

        return true;


    }

}
