package main;

import javax.swing.*;
import java.awt.*;

import static main.Data.Directions.*;
import static main.Data.Display.GameScale;

/**
 * @author Aurelijus Lukšas 5 group.
 * Class used for setting the frame size and starting render loop.
 */
public class GameContent extends JPanel {
    private final Game game;
    private InputHandler inputHandler;

    /**
     * @author Aurelijus Lukšas 5 group.
     * This constructor set the size of the frame and add Key and Mouse listeners.
     * @param game
     */
    public  GameContent(Game game) {
        this.game = game;
        setSize();
        inputHandler = new InputHandler(this);
        addKeyListener(inputHandler);
        addMouseListener(inputHandler);
        addMouseMotionListener(inputHandler);
        this.setFocusable(true);
        this.requestFocusInWindow();
    }

    /**
     * @author Aurelijus Lukšas 5 group.
     * This method sets the size of the window in which game is played.
     */
    public void setSize() {
        Dimension size = new Dimension((int)(960 * GameScale), (int)(640 * GameScale));
        setMinimumSize(size);
        setPreferredSize(size);
        setMaximumSize(size);
    }

    /**
     * This method is used for looping the render function that is repeated in most of the classes.
     * @param g the <code>Graphics</code> object to protect
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g); // Pre-work, after this code below executes.
        game.render(g);

    }

    /**
     * Game class getter.
     * @return
     */
    public Game getGame() {
        return game;
    }


}
