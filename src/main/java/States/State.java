package States;
import GUI.EditorButtons;
import GUI.MenuButtons;
import main.Game;

import java.awt.event.MouseEvent;

/**
 * @author Aurelijus Lukšas 5 group.
 * Class used for setting the current game state.
 */
public class State {

    protected Game game;

    /**
     * Constructor used for getting game class.
     * @param game
     */
    public State(Game game) {
        this.game = game;
    }

    /**
     * Checks if mouse is in a button from menu.
     * @param e
     * @param mb
     * @return
     */
    public boolean isInButton(MouseEvent e, MenuButtons mb) {
        return mb.getBounds().contains(e.getX(),e.getY());
    }

    /**
     * Checks if mouse is in a button from editor.
     * @param e
     * @param mb
     * @return
     */
    public boolean isInEditorButton(MouseEvent e, EditorButtons mb) {
        return mb.getBounds().contains(e.getX(),e.getY());
    }

    /**
     * Game getter.
     * @return
     */
    public Game getGame() {
        return game;
    }
}