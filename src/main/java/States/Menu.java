package States;

import static main.Data.Display.*;
import static main.Data.Buttons.Menu.*;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import main.Data;
import main.Game;
import GUI.MenuButtons;
import main.LoadGraphics;

/**
 * @author Aurelijus Lukšas 5 group.
 * Class used for when the game state is menu.
 */
public class Menu extends State implements StateMethods {
    private MenuButtons[] menuButtons = new MenuButtons[4];
    private BufferedImage background;

    /**
     * Constructor used for loading the methods and variables of the menu class.
     * @param game
     */
    public Menu(Game game) {
        super(game);

        background = LoadGraphics.GetSpriteAtlas(LoadGraphics.menuBackground);
        loadButtons();
    }

    /**
     * Method used for creating the buttons of the menu.
     */
    private void loadButtons() {
        menuButtons[0] = new MenuButtons(ScreenWidth / 2, 200, Start, GameState.PLAY);
        menuButtons[1] = new MenuButtons(ScreenWidth / 2, 300, Custom, GameState.EDITOR);
        menuButtons[2] = new MenuButtons(ScreenWidth / 2, 400, Options, GameState.OPTIONS);
        menuButtons[3] = new MenuButtons(ScreenWidth / 2, 500, Quit, GameState.QUIT);}

    /**
     * Method used for updating the buttons.
     */
    @Override
    public void update() {
        for (MenuButtons mb : menuButtons)
            mb.update();
    }

    /**
     * Method used for drawing the menu buttons.
     * @param g
     */
    @Override
    public void draw(Graphics g) {
        g.drawImage(background, 0, 0, null);
        for (MenuButtons mb : menuButtons)
            mb.draw(g);

    }

    /**
     * Method used for checking if the mouse was clicked in the menu.
     * @param e
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    /**
     * Method used for checking if the mouse was pressed in the menu.
     * @param e
     */
    @Override
    public void mousePressed(MouseEvent e) {
        for (MenuButtons mb : menuButtons) {
            if (isInButton(e,mb)) {
                mb.setMousePressed(true);
            }
        }


    }

    /**
     * Method used for checking if the mouse was released in the menu.
     * @param e
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        for (MenuButtons mb : menuButtons) {
            if (isInButton(e,mb)) {
                if (mb.isMousePressed())
                    mb.applyGamestate();
                break;
            }
        }
        resetButtons();

    }

    /**
     * Method used for resetting the menu button booleans.
     */
    private void resetButtons() {
        for (MenuButtons mb : menuButtons) {
            mb.resetBools();
        }
    }

    /**
     * Method used for checking if the mouse has moved in the menu.
     * @param e
     */
    @Override
    public void mouseMoved(MouseEvent e) {
        for (MenuButtons mb : menuButtons) {
            mb.setMouseHover(false);
        }
        for (MenuButtons mb : menuButtons) {
            if (isInButton(e,mb)) {
                mb.setMouseHover(true);
                break;
            }
        }
    }

    /**
     * Method used for checking if the mouse has been draged in the menu.
     * @param e
     */
    @Override
    public void mouseDragged(MouseEvent e) {

    }

    /**
     * Method used for checking if a key was pressed in the menu.
     * @param e
     */
    @Override
    public void keyPressed(KeyEvent e) {

    }

    /**
     * Method used for checking if a key was released in the menu.
     * @param e
     */
    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub

    }

}