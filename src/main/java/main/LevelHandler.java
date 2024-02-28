package main;

import misc.ReadJSON;

import java.awt.*;
import java.awt.image.BufferedImage;
import static main.Data.Display.GameScale;
import static main.Data.Display.ScreenWidth;

/**
 * @author Aurelijus Lukšas 5 group.
 * Class used for drawing and handling all the level data.
 */
public class LevelHandler {
    public static boolean gameStart = false;
    private int counterTick = 0, counterIndex = 1;
    private BufferedImage tileSet;
    private BufferedImage[] tileMap;
    public static int startX, startY;
    public static int[][] solidData, backData, frontData;
    private static final int Width = Data.Display.TilesWidth;
    public static String currentLevel = "level1.json";
    public static int levelHeight = 20, levelWidth = 40;

    /**
     * Constructor used for starting new methods and variables.
     * @param game
     */
    public LevelHandler(Game game) {
        tileSet = LoadGraphics.GetSpriteAtlas(LoadGraphics.mapSprite);
        importTileset();
        solidData = new int[levelHeight][levelWidth];
        backData = new int[levelHeight][levelWidth];
        frontData = new int[levelHeight][levelWidth];
        loadMap();
    }

    /**
     * Method used for loading the map data.
     */
    public static void loadMap() {
        solidData = ReadJSON.readLevel(currentLevel, 0);
        backData = ReadJSON.readLevel(currentLevel, 1);
        frontData = ReadJSON.readLevel(currentLevel, 2);
        getStartPositions();
    }

    /**
     * Method used for getting the players start position.
     */
    private static void getStartPositions() {
        for (int i = 0; i < levelHeight; i++) {
            for (int j = 0; j < levelWidth; j++) {
                if (solidData[i][j] == 59) {
                    startX = j * 32;
                    startY = i * 32;
                }
            }
        }
    }

    /**
     * Method used for importing the tileset for png in to a Buffered image array.
     */
    private void importTileset() {
        tileMap = new BufferedImage[77];
        tileMap[0] = LoadGraphics.GetSpriteAtlas(LoadGraphics.emptyTile);
        int index = 1;
        for (int i = 0; i < 19; i++) {
            for (int j = 0; j < 4; j++) {
                tileMap[index] = tileSet.getSubimage(1 + 33 * j,1 + 33 * i,32,32);
                index++;
            }
        }

    }

    /**
     * Method used for drawing the map.
     * @param g
     * @param levelOffsetX
     */
    public void draw(Graphics g, int levelOffsetX) {
        for (int i = 0; i < levelHeight; i++) {
            for (int j = 0; j < levelWidth; j++) {
                g.drawImage(tileMap[backData[i][j]], (int)(GameScale * Width * j - levelOffsetX), (int)(GameScale * Width * i), (int)(32 * GameScale), (int)(32 * GameScale), null);
                g.drawImage(tileMap[solidData[i][j]], (int)(GameScale * Width * j - levelOffsetX), (int)(GameScale * Width * i), (int)(32 * GameScale), (int)(32 * GameScale), null);
            }
        }
    }

    /**
     * Method used for drawing the map which is seen in front of the player.
     * @param g
     * @param levelOffsetX
     */
    public void drawInFront(Graphics g, int levelOffsetX) {
        for (int i = 0; i < levelHeight; i++) {
            for (int j = 0; j < levelWidth; j++) {
                g.drawImage(tileMap[frontData[i][j]], (int)(GameScale * Width * j - levelOffsetX), (int)(GameScale * Width * i), (int)(32 * GameScale), (int)(32 * GameScale), null);
            }
        }
        drawStarter(g);
    }
    private void drawStarter(Graphics g) {
        if (gameStart) {
            Font fonts = new Font("Arial", Font.BOLD, 100);
            g.setColor(Color.white);
            g.setFont(fonts);
            if (counterIndex == 1) {
                g.drawString("1", ScreenWidth / 2 - 50, 300);
            }
            if (counterIndex == 2) {
                g.drawString("2", ScreenWidth / 2 - 50, 300);
            }
            if (counterIndex == 3) {
               g.drawString("3", ScreenWidth / 2 -50, 300);
            }
            if (counterIndex == 4) {
                g.drawString("START!", ScreenWidth / 2 - 200, 300);
            }
        }
    }
    private void getCounterIndex() {
        if (gameStart) {
            counterTick++;
            int counterSpeed = 200;
            if (counterTick >= counterSpeed) {
                counterTick = -1;
                counterIndex++;
                if (counterIndex == 5) {
                    gameStart = false;
                    counterIndex = 1;
                }
            }
        }
    }

    /**
     * Method used for updating everything that's happening in the map.
     */
    public void update() {
        getCounterIndex();
    }
}
