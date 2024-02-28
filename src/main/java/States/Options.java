package States;

import main.Game;
import main.LevelHandler;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * @author Aurelijus Lukšas 5 group.
 * Class used when the game state is changed to options.
 * Not complete, just a template.
 */

public class Options extends State implements StateMethods{
    /**
     * Constructor used to get the game functions.
     * @param game
     */
    public Options(Game game) {
        super(game);
    }

    /**
     * Method used to update everything calculated in the options class.
     */
    @Override
    public void update() {

    }

    /**
     * Method used to render everything in the menu state.
     * @param g
     */
    @Override
    public void draw(Graphics g) {

    }

    /**
     * Used for checking if the mouse was clicked.
     * @param e
     */
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    /**
     * Used for checking if the mouse was pressed.
     * @param e
     */
    @Override
    public void mousePressed(MouseEvent e) {

    }

    /**
     * Used for checking if the mouse was released in options.
     * @param e
     */
    @Override
    public void mouseReleased(MouseEvent e) {

    }

    /**
     * Used for checking if mouse has moved in options.
     * @param e
     */
    @Override
    public void mouseMoved(MouseEvent e) {

    }

    /**
     * Used for checking if mouse has been dragged in options.
     * @param e
     */
    @Override
    public void mouseDragged(MouseEvent e) {

    }

    /**
     * Used for checking if a key was pressed in options.
     * @param e
     */
    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_ESCAPE:
                GameState.state = GameState.MENU;
                break;
        }
    }

    /**
     * Used for checking if a key was released in options.
     * @param e
     */
    @Override
    public void keyReleased(KeyEvent e) {

    }
}
