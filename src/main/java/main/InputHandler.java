package main;

import States.GameState;

import java.awt.event.*;


/**
 * @author Aurelijus Lukšas 5 group.
 * Class used for handling all the user inputs.
 */
public class InputHandler implements KeyListener, MouseListener, MouseMotionListener {
    private final GameContent gameContent;

    /**
     * Constructor used for setting the Game content variable to game content class.
     * @param gameContent
     */
    public InputHandler(GameContent gameContent) {
        this.gameContent = gameContent;

    }

    /**
     * Checks if the key was typed.
     * @param e the event to be processed
     */
    @Override
    public void keyTyped(KeyEvent e) {

    }

    /**
     * Checks if the was released.
     * @param e the event to be processed
     */
    @Override
    public void keyReleased(KeyEvent e) {
        switch (GameState.state) {
            case MENU:
                gameContent.getGame().getMenu().keyReleased(e);
                break;
            case PLAY:
                gameContent.getGame().getPlaying().keyReleased(e);
                break;
            default:
                break;

        }
    }

    /**
     * Checks if the key was pressed.
     * @param e the event to be processed
     */
    @Override
    public void keyPressed(KeyEvent e) {
        switch (GameState.state) {
            case MENU:
                gameContent.getGame().getMenu().keyPressed(e);
                break;
            case PLAY:
                gameContent.getGame().getPlaying().keyPressed(e);
                break;
            case EDITOR:
                gameContent.getGame().getEditor().keyPressed(e);
            case OPTIONS:
                gameContent.getGame().getOptions().keyPressed(e);
            default:
                break;
        }
    }

    /**
     * Checks if the mouse was clicked.
     * @param e the event to be processed
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        switch (GameState.state) {
            case MENU:
                gameContent.getGame().getMenu().mouseClicked(e);
                break;
            case PLAY:
                gameContent.getGame().getPlaying().mouseClicked(e);
                break;
            case EDITOR:
                gameContent.getGame().getEditor().mouseClicked(e);
            default:
                break;

        }

    }

    /**
     * Checks if the mouse was pressed.
     * @param e the event to be processed
     */
    @Override
    public void mousePressed(MouseEvent e) {
        switch (GameState.state) {
            case MENU:
                gameContent.getGame().getMenu().mousePressed(e);
                break;
            case PLAY:
                gameContent.getGame().getPlaying().mousePressed(e);
                break;
            case EDITOR:
                gameContent.getGame().getEditor().mousePressed(e);
            default:
                break;

        }

    }

    /**
     * Checks if the mouse was released.
     * @param e the event to be processed
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        switch (GameState.state) {
            case MENU:
                gameContent.getGame().getMenu().mouseReleased(e);
                break;
            case PLAY:
                gameContent.getGame().getPlaying().mouseReleased(e);
                break;
            case EDITOR:
                gameContent.getGame().getEditor().mouseReleased(e);
            default:
                break;

        }

    }

    /**
     * Checks if the mouse entered a certain position.
     * @param e the event to be processed
     */
    @Override
    public void mouseEntered(MouseEvent e) {

    }

    /**
     * Checks if the mouse exits a certain position.
     * @param e the event to be processed
     */
    @Override
    public void mouseExited(MouseEvent e) {

    }

    /**
     * Checks if the mouse was dragged.
     * @param e the event to be processed
     */
    @Override
    public void mouseDragged(MouseEvent e) {
        switch (GameState.state) {
            case EDITOR:
                gameContent.getGame().getEditor().mouseDragged(e);
            default:
                break;

        }
    }

    /**
     * Checks if the mouse has been moved.
     * @param e the event to be processed
     */
    @Override
    public void mouseMoved(MouseEvent e) {
        switch (GameState.state) {
            case MENU:
                gameContent.getGame().getMenu().mouseMoved(e);
                break;
            case PLAY:
                gameContent.getGame().getPlaying().mouseMoved(e);
                break;
            case EDITOR:
                gameContent.getGame().getEditor().mouseMoved(e);
            default:
                break;

        }
    }
}
