package GUI;

import States.Editor;
import States.GameState;
import main.LoadGraphics;

import java.awt.*;
import java.awt.image.BufferedImage;
import States.Editor.*;
import misc.WriteJSON;

import javax.swing.*;

/**
 * @author Aurelijus Lukšas 5 group.
 * Class used for generating editor buttons.
 */
public class EditorButtons {
    private int x, y, rowIndex, index;
    public static BufferedImage[][] buttonSelect;
    private int centerOffset = 81;
    private GameState state;
    private int layerIndex;
    private boolean mousePressed, mouseHover;
    private Rectangle box;

    /**
     * Constructor used for setting the button position and its information contained.
     * @param x
     * @param y
     * @param rowIndex
     * @param layerIndex
     */
    public EditorButtons(int x, int y, int rowIndex, int layerIndex) {
        this.x = x;
        this.y = y;
        this.layerIndex = layerIndex;
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
     * Method used for getting button animations from png in to a Buffered image array.
     * @param row
     */
    private void getButtonSprites(int row) {
        buttonSelect = new BufferedImage[5][3];
        BufferedImage buttonSprite = LoadGraphics.GetSpriteAtlas(LoadGraphics.editorButtons);
        for (int j = 0; j < 5; j++) {
            for (int i = 0; i < 3; i++) {
                buttonSelect[j][i] = buttonSprite.getSubimage(i * 81, j * 17, 81, 17);
            }
        }
    }

    /**
     * Method used for updating the button animations based on mouse actions.
     */
    public void update() {
        index = 0;
        if (layerIndex == 1 && Editor.solid)
            index = 2;
        if (mouseHover) {
            index = 1;
        }
        if (mousePressed) {
            index = 2;
        }
    }

    /**
     * Method used to draw the button.
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
        switch (layerIndex) {
            case 1:
               Editor.solid = true;
               Editor.behind = false;
               Editor.inFront = false;
               break;
            case 2:
                Editor.solid = false;
                Editor.behind = true;
                Editor.inFront = false;
                break;
            case 3:
                Editor.solid = false;
                Editor.behind = false;
                Editor.inFront = true;
                break;
        }
        if (layerIndex == 4) {
            Editor.saveInput = JOptionPane.showInputDialog("Input file name to save the map: ");
            if (Editor.saveInput != null)
                Editor.write = true;
        }
        if (layerIndex == 5) {
            Editor.loadInput = JOptionPane.showInputDialog("Input file name to load the map: ");
            if (Editor.loadInput != null)
                Editor.read = true;
        }

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
     * Method used for applying gamestate.
     */
    public void applyGamestate() {

    }

    /**
     * Method used for resetting the booleans of buttons.
     */
    public void resetBools() {
        mouseHover = false;
        if (layerIndex == 1 && !Editor.solid) {
            mousePressed = false;
        }
        if (layerIndex == 2 && !Editor.behind) {
            mousePressed = false;
        }
        if (layerIndex == 3 && !Editor.inFront) {
            mousePressed = false;
        }
        if (layerIndex == 4 || layerIndex == 5)
            mousePressed = false;
    }
}
