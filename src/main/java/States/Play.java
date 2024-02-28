package States;

import main.*;
import misc.ReadJSON;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import static main.Data.Display.*;

/**
 * @author Aurelijus Lukšas 5 group.
 * Class used when the game state is play.
 */
public class Play extends State implements StateMethods{
    private LevelHandler levelHandler;
    private static Player player;
    private BufferedImage Sky1, Sky2, Sky3, Sky4;

    private int LevelOffsetX;

    /**
     * Constructor used for getting game methods and starting classes.
     * @param game
     */
    public Play(Game game) {
        super(game);
        startClasses();
        loadSky();
    }

    /**
     * Method used for checking the screen borders and setting the map offset.
     */
    private void checkBorder() {
        int playerX = (int)player.getHitbox().x;
        int diffX = playerX - LevelOffsetX;

        if(diffX > RightBorder)
            LevelOffsetX += diffX - RightBorder;
        else if(diffX < LeftBorder)
            LevelOffsetX += diffX - LeftBorder;

        if(LevelOffsetX > maxLevelOffsetX)
            LevelOffsetX = maxLevelOffsetX;
        else if(LevelOffsetX < 0)
            LevelOffsetX = 0;

    }

    /**
     * Method used for starting classes.
     */
    private void startClasses() {
        levelHandler = new LevelHandler(game);
        player = new Player(120, 200, 32, 32);
        getMapData();
    }

    /**
     * Method used for getting the current level data.
     */
    public static void getMapData() {
        player.loadData(ReadJSON.readLevel(LevelHandler.currentLevel, 0));
        player.resetPlayerPos();
    }

    /**
     * Player class getter.
     * @return
     */
    public Player getPlayer(){
        return player;
    }

    /**
     * Method used for updating everything that is happening in play class.
     */
    @Override
    public void update() {
        levelHandler.update();
        player.update();
        checkBorder();
    }

    /**
     * Method used to render everything that need to be seen in play state.
     * @param g
     */
    @Override
    public void draw(Graphics g) {
        drawSky(g);
        levelHandler.draw(g, LevelOffsetX);
        player.render(g, LevelOffsetX);
        levelHandler.drawInFront(g, LevelOffsetX);

    }

    /**
     * Method used for loading the background sky files.
     */
    public void loadSky() {
        Sky1 = LoadGraphics.GetSpriteAtlas(LoadGraphics.Sky1);
        Sky2 = LoadGraphics.GetSpriteAtlas(LoadGraphics.Sky2);
        Sky3 = LoadGraphics.GetSpriteAtlas(LoadGraphics.Sky3);
        Sky4 = LoadGraphics.GetSpriteAtlas(LoadGraphics.Sky4);
    }

    /**
     * Method used for drawing the background sky with parallax effect.
     * @param g
     */
    private void drawSky(Graphics g) {

        g.drawImage(Sky1,0,0,TilesWidth * MapWidth, TilesWidth * MapHeight, null);
        g.drawImage(Sky2,0,0,TilesWidth * MapWidth, TilesWidth * MapHeight, null);

        for (int i = 0; i < 2; i++) {
            g.drawImage(Sky3,i * TilesWidth * MapWidth - (int) (LevelOffsetX * 0.05),0,TilesWidth * MapWidth, TilesWidth * MapHeight, null);
        }
        for (int i = 0; i < 2; i++) {
            g.drawImage(Sky4,i * TilesWidth * MapWidth - (int) (LevelOffsetX * 0.1),0,TilesWidth * MapWidth, TilesWidth * MapHeight, null);
        }
    }

    /**
     * Checks if the mouse was clicked in play state.
     * @param e
     */
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    /**
     * Checks if the mouse was pressed in play state.
     * @param e
     */
    @Override
    public void mousePressed(MouseEvent e) {

    }

    /**
     * Checks if the mouse was released in play state.
     * @param e
     */
    @Override
    public void mouseReleased(MouseEvent e) {

    }

    /**
     * Checks if the mouse has moved in the play state.
     * @param e
     */
    @Override
    public void mouseMoved(MouseEvent e) {

    }

    /**
     * Checks if the mouse has been dragged in the play state.
     * @param e
     */
    @Override
    public void mouseDragged(MouseEvent e) {

    }

    /**
     * Checks if a key was pressed in the play state.
     * @param e
     */
    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_A:
                player.setLeft(true);
                break;
            case KeyEvent.VK_D:
                player.setRight(true);
                break;
            case KeyEvent.VK_SPACE, KeyEvent.VK_W:
                player.setJump(true);
                break;
            case KeyEvent.VK_ESCAPE:
                GameState.state = GameState.MENU;
                break;
        }
    }

    /**
     * Checks if a key was released in the play state.
     * @param e
     */
    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_A:
                player.setLeft(false);
                break;
            case KeyEvent.VK_D:
                player.setRight(false);
                break;
            case KeyEvent.VK_SPACE, KeyEvent.VK_W:
                player.setJump(false);
                break;
        }
    }
}
