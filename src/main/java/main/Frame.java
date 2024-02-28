package main;

import javax.swing.*;

import static main.Data.Display.*;

/**
 * @author Aurelijus Lukšas 5 group.
 * Class used for adding a main frame.
 */
public class Frame {
    /**
     * @author Aurelijus Lukšas 5 group.
     * Adds main frame in which the game is displayed.
     * @param gameContent Class of the game content which contain inputs and starts other classes.
     */
    public Frame(GameContent gameContent) {
        JFrame jframe = new JFrame();
        jframe.setResizable(false);
        jframe.add(gameContent);
        jframe.pack();
        jframe.setLocationRelativeTo(null);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        jframe.setVisible(true);
    }
}
