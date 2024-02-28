package main;

import States.*;
import States.Menu;
import misc.ReadJSON;
import misc.WriteJSON;

import java.awt.*;

/**
 * @author Aurelijus Lukšas 5 group.
 * Class which starts all the other classes.
 */
public class Game implements Runnable {
    private Play playing;
    private Menu menu;
    private Editor editor;
    private Options options;
    private ReadJSON readJSON;
    private WriteJSON writeJSON;
    private final int FPS = 120;
    private final int UPS = 200;
    private final GameContent gameContent;

    /**
     * @author Aurelijus Lukšas 5 group.
     * This constructor starts classes and other methods of the game loop.
     */
    public Game() {
        startClasses();

        gameContent = new GameContent(this);

        new Frame(gameContent);
        gameContent.requestFocus();
        gameLoop();
    }

    /**
     * @author Aurelijus Lukšas 5 group.
     * Method which is specifically used to start classes.
     */
    private void startClasses() {
        playing = new Play(this);
        menu = new Menu(this);
        editor = new Editor(this);
        options = new Options(this);

        writeJSON = new WriteJSON();
        readJSON = new ReadJSON();


    }

    /**
     * @author Aurelijus Lukšas 5 group.
     * This method starts a new game thread.
     */
    private void gameLoop() {
        Thread gameThread = new Thread(this);
        gameThread.start();
    }

    /**
     * @author Aurelijus Lukšas 5 group.
     * This method is used to update everything happening in Game class.
     */
    public void update() {
        switch (GameState.state){
            case PLAY -> {
                playing.update();
            }
            case MENU -> {
                menu.update();
            }
            case EDITOR -> {
                editor.update();
            }
            case OPTIONS -> {
                options.update();
            }
            case QUIT -> {
                System.exit(0);
            }
        }
    }

    /**
     * @author Aurelijus Lukšas 5 group.
     * This method is used to call render methods of other classes.
     * @param g
     */
    public void render(Graphics g) {
        switch (GameState.state){
            case PLAY -> {
                playing.draw(g);
            }
            case MENU -> {
                menu.draw(g);
            }
            case EDITOR -> {
                editor.draw(g);
            }
            case OPTIONS -> {
                options.draw(g);
            }

        }
    }

    /**
     * @author Aurelijus Lukšas 5 group.
     * This method is used to run the game loop.
     */
    @Override
    public void run() {

        double timePerFrame = 1000000000.0 / FPS;
        double timePerUpdate = 1000000000.0 / UPS;

        long previousTime = System.nanoTime();

        int frames = 0;
        int updates = 0;
        long lastCheck = System.currentTimeMillis();

        double deltaU = 0;
        double deltaF = 0;

        while (true) {
            long currentTime = System.nanoTime();

            deltaU += (currentTime - previousTime) / timePerUpdate;
            deltaF += (currentTime - previousTime) / timePerFrame;
            previousTime = currentTime;

            if (deltaU >= 1) {
                update();
                updates++;
                deltaU--;
            }

            if (deltaF >= 1) {
                gameContent.repaint();
                frames++;
                deltaF--;
            }

            if (System.currentTimeMillis() - lastCheck >= 1000) {
                lastCheck = System.currentTimeMillis();
                frames = 0;
                updates = 0;

            }
        }

    }

    /**
     * Menu getter.
     * @return
     */
    public Menu getMenu() {
        return menu;
    }

    /**
     * Play state getter.
     * @return
     */
    public Play getPlaying() {
        return playing;
    }

    /**
     * Edit state getter.
     * @return
     */
    public Editor getEditor() {
        return editor;
    }

    /**
     * Options state getter.
     * @return
     */
    public Options getOptions() {
        return options;
    }
}
