package GUI;

import States.GameState;
import main.LevelHandler;
import main.LoadGraphics;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * @author Aurelijus Lukšas 5 group.
 * Class used for creating menu buttons.
 */
public class MenuButtons {
    private int x, y, rowIndex, index;
    public static BufferedImage[][] buttonSelect;
    private int centerOffset = 81;
    private GameState state;
    private boolean mousePressed, mouseHover;
    private Rectangle box;

    /**
     * Constructor which sets the buttons position and other information.
     * @param x
     * @param y
     * @param rowIndex
     * @param state
     */
    public MenuButtons(int x, int y, int rowIndex, GameState state) {
        this.x = x;
        this.y = y;
        this.state = state;
        this.rowIndex = rowIndex;
            getButtonSprites(rowIndex);
            createButtonHitbox();
    }

    /**
     * Method used for creating buttons hitbox.
     */
    private void createButtonHitbox() {
        box = new Rectangle(x - centerOffset, y, 162, 34);
    }

    /**
     * Metohd used for getting menu buttons animation as an array from png file.
     * @param row
     */
    private void getButtonSprites(int row) {
        buttonSelect = new BufferedImage[4][3];
        BufferedImage buttonSprite = LoadGraphics.GetSpriteAtlas(LoadGraphics.menuButtons);
        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < 3; i++) {
                buttonSelect[j][i] = buttonSprite.getSubimage(i * 81, j * 17, 81, 17);
            }
        }
    }

    /**
     * Method used for updating button animation based on mouse actions.
     */
    public void update() {
        index = 0;
        if (mouseHover) {
            index = 1;
        }
        if (mousePressed) {
            index = 2;
        }
    }

    /**
     * Method used for drawing the menu buttons.
     * @param g
     */
    public void draw(Graphics g) {
        g.drawImage(buttonSelect[rowIndex][index], x - centerOffset, y, 162, 34, null);
    }

    /**
     * Method used for checking if the mouse is pressed.
     * @return
     */
    public boolean isMousePressed() {
        return mousePressed;
    }

    /**
     * Mouse pressed setter.
     * @param mousePressed
     */
    public void setMousePressed(boolean mousePressed) {
        this.mousePressed = mousePressed;
    }

    /**
     * Mouse hover setter.
     * @param mouseHover
     */
    public void setMouseHover(boolean mouseHover) {
        this.mouseHover = mouseHover;
    }

    /**
     * Button hitbox getter.
     * @return
     */
    public Rectangle getBounds() {
        return box;
    }

    /**
     * Method used for applying game state.
     */
    public void applyGamestate() {
        GameState.state = state;
        if (GameState.state == GameState.PLAY) {
            LevelHandler.gameStart = true;
        }
    }

    /**
     * Method used for resetting button booleans.
     */
    public void resetBools() {
        mouseHover = false;
        mousePressed = false;
    }
}
