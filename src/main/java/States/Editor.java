package States;

import GUI.EditorButtons;
import main.Game;
import main.LevelHandler;
import main.LoadGraphics;
import misc.ReadJSON;
import misc.WriteJSON;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import static main.Data.Buttons.Editor.*;

/**
 * @author Aurelijus Lukšas 5 group.
 * Class of the Editor state.
 */
public class Editor extends State implements StateMethods {

    private final BufferedImage tileSet;
    public static boolean write = false, read = false;
    public static boolean solid = true, behind = false, inFront = false;
    public static String loadInput, saveInput;
    private final BufferedImage background;
    private int[][] solidData;
    private int[][] frontData;
    private int[][] backData;
    private boolean mouseInSelector = false, mouseInMap = false;
    private BufferedImage[] tileMap;
    private int xOffset = 0, yOffset = 0;
    private int Xmap, Ymap, Xtile, Ytile, tileID, tileIDSelected = 1, mapX, mapY;
    private EditorButtons[] editorButtons = new EditorButtons[5];

    /**
     * Constructor used for starting the methods and new variables.
     * @param game
     */
    public Editor(Game game) {
        super(game);
        solidData = new int[20][40];
        backData = new int[20][40];
        frontData = new int[20][40];
        tileSet = LoadGraphics.GetSpriteAtlas(LoadGraphics.mapSprite);

        background = LoadGraphics.GetSpriteAtlas(LoadGraphics.backgroundColor);
        loadButtons();
        loadTileset();
    }

    /**
     * Method used for loading the tileset from a png to a Buffered image array.
     */
    private void loadTileset() {
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
     * Method used for drawing Tile set grid which is located on the right of the screen.
     * @param g
     */
    private void drawTileSetGrid(Graphics g) {
        g.setColor(Color.lightGray);
        for (int i = 0; i < 5; i++) {
            g.drawLine(827 + i * 33,0,827 + i * 33,627);
        }
        for (int i = 0; i < 20; i++) {
            g.drawLine(827, i * 33, 959, i * 33);
        }
    }

    /**
     * Method used for drawing map grid.
     * @param g
     */
    private void drawMapGrid(Graphics g) {
        g.setColor(Color.lightGray);
        for (int i = 0; i < 17; i++) {
            g.drawLine(10, 128 + i * 32, 810, 128 + i * 32);
        }
        for (int i = 0; i < 26; i++) {
            g.drawLine(10 + i * 32, 128, 10 + i * 32, 608);
        }
    }

    /**
     * Method used for loading the editor buttons.
     */
    private void loadButtons() {
        editorButtons[0] = new EditorButtons(399, 10, Layer1, 1);
        editorButtons[1] = new EditorButtons(563, 10, Layer2, 2);
        editorButtons[2] = new EditorButtons(727, 10, Layer3, 3);
        editorButtons[3] = new EditorButtons(70, 10, Save, 4);
        editorButtons[4] = new EditorButtons(70, 60, Load, 5);
    }

    /**
     * Method used for calling the update of editor buttons.
     */
    @Override
    public void update() {
        saveFile();
        getMapData();
        for (EditorButtons ed : editorButtons)
            ed.update();
    }

    /**
     * Method used for getting the map data in the editor.
     */
    private void getMapData() {
        if (read) {
            solidData = ReadJSON.readLevel(loadInput, 0);
            backData = ReadJSON.readLevel(loadInput, 1);
            frontData = ReadJSON.readLevel(loadInput, 2);
            read = false;
        }
    }

    /**
     * Method used for checking if the save button was pressed and then writing the newly generated data into a json file.
     */
    private void saveFile() {
        if (write) {
            WriteJSON.writeToJSON(solidData,frontData,backData,saveInput);
            write = false;
        }
    }

    /**
     * Method used for calling all the draw methods of the editor.
     * @param g
     */
    @Override
    public void draw(Graphics g) {
        g.drawImage(background,0,0, null);
        g.drawImage(tileSet,827,0, null);
        drawTileSetGrid(g);
        drawMapGrid(g);
        drawSampleTile(g);
        drawMap(g);
        drawSelector(g);
        drawMapSelector(g);
        for (EditorButtons ed : editorButtons)
            ed.draw(g);
    }

    /**
     * Method used for drawing the map selector which follow the mouse.
     * @param g
     */
    private void drawMapSelector(Graphics g) {
        if (mouseInMap) {
            int x = 10;
            int y = 128;
            g.setColor(new Color(128, 128, 128, 160));
            g.drawImage(tileMap[tileIDSelected],x + Xmap * 32, y + Ymap * 32, null);
        }
    }

    /**
     * Method used for drawing the tile selector block which is seen when hovering over the tileset.
     * @param g
     */
    private void drawSelector(Graphics g) {
        if (mouseInSelector) {
            int xZero = 828;
            int yZero = 1;
            g.setColor(new Color(128, 128, 128, 160));
            g.fillRect(xZero + (Xtile - 1) * 33, yZero + (Ytile - 1) * 33, 32,32);
        }
    }

    /**
     * Method used for drawing the map.
     * @param g
     */
    private void drawMap(Graphics g) {
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 25; j++) {
                g.drawImage(tileMap[backData[i + yOffset][j + xOffset]], 10 + j * 32, 128 + i * 32, null);
                g.drawImage(tileMap[solidData[i + yOffset][j + xOffset]], 10 + j * 32, 128 + i * 32, null);
                g.drawImage(tileMap[frontData[i + yOffset][j + xOffset]], 10 + j * 32, 128 + i * 32, null);
            }
        }
    }

    /**
     * Method used for drawing the sample tile wich is seen over the map.
     * @param g
     */
    private void drawSampleTile(Graphics g) {
        g.drawImage(tileMap[tileIDSelected],190,30, 64, 64, null);
        g.drawRect(185,25,73,73);
    }

    /**
     * Method used for checking if the mouse was clicked.
     * @param e
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1)
            if (e.getX() > 827 && e.getX() < 959)
                if (e.getY() > 0 && e.getY() < 627)
                    tileIDSelected = tileID;
    }

    /**
     * Method used for checking if the mouse was pressed.
     * @param e
     */
    @Override
    public void mousePressed(MouseEvent e) {
        for (EditorButtons ed : editorButtons) {
            if (isInEditorButton(e,ed)) {
                ed.setMousePressed(true);
            }
        }
        switch (e.getButton()){
            case MouseEvent.BUTTON1:
                if (e.getX() >= 10 && e.getX() <= 810) {
                    if (e.getY() >= 128 && e.getY() <= 608) {
                        if (solid)
                            solidData[Ymap + yOffset][Xmap + xOffset] = tileIDSelected;
                        if (behind)
                            backData[Ymap + yOffset][Xmap + xOffset] = tileIDSelected;
                        if (inFront)
                            frontData[Ymap + yOffset][Xmap + xOffset] = tileIDSelected;
                    }
                }
                break;
            case MouseEvent.BUTTON3:
                if (e.getX() >= 10 && e.getX() <= 810) {
                    if (e.getY() >= 128 && e.getY() <= 608) {
                        if (solid)
                            solidData[Ymap + yOffset][Xmap + xOffset] = 0;
                        if (behind)
                            backData[Ymap + yOffset][Xmap + xOffset] = 0;
                        if (inFront)
                            frontData[Ymap + yOffset][Xmap + xOffset] = 0;
                    }
                }
                break;
        }

    }

    /**
     * Method used for checking if the mouse was released.
     * @param e
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        for (EditorButtons ed : editorButtons) {
            if (isInEditorButton(e,ed)) {
                if (ed.isMousePressed())
                    ed.applyGamestate();
                break;
            }
        }
        resetButtons();

    }

    /**
     * Method used for resetting the buttons of map editor.
     */
    private void resetButtons() {
        for (EditorButtons ed : editorButtons) {
            ed.resetBools();
        }
    }

    /**
     * Method used for checking if the mouse has moved.
     * @param e
     */
    @Override
    public void mouseMoved(MouseEvent e) {
        for (EditorButtons ed : editorButtons) {
            ed.setMouseHover(false);
        }
        for (EditorButtons ed : editorButtons) {
            if (isInEditorButton(e,ed)) {
                ed.setMouseHover(true);
                break;
            }
        }
        if (e.getX() >= 10 && e.getX() <= 810) {
            if (e.getY() >= 128 && e.getY() <= 608) {
                mouseInMap = true;
                mapX = e.getX() - 10;
                mapY = e.getY() - 128;
                getMapTile(mapX, mapY);
            } else mouseInMap = false;
        } else mouseInMap = false;

        if (e.getX() > 827 && e.getX() < 959) {
            if (e.getY() > 0 && e.getY() < 627) {
                mouseInSelector = true;
                int tileX = e.getX() - 827;
                int tileY = e.getY();
                getTilesetID(tileX, tileY);
            } else mouseInSelector = false;
        } else mouseInSelector = false;
    }

    /**
     * Method for checking if the mouse has been dragged in map editor.
     * @param e
     */
    @Override
    public void mouseDragged(MouseEvent e) {

    }

    /**
     * Method used for getting the id of the tile which the mouse is hovering on.
     * @param x
     * @param y
     */
    private void getTilesetID(int x, int y) {
        Xtile = Xmap = (int)(x / 33) + 1;
        Ytile = (int)(y / 33) + 1;
        tileID = (Ytile - 1) * 4 + Xtile;
    }

    /**
     * Method used for getting the map tile on which the mouse is hovering.
     * @param x
     * @param y
     */
    private void getMapTile(int x, int y) {
        Xmap = (int)(x / 32);
        Ymap = (int)(y / 32);
    }

    /**
     * Method for checking if the key was pressed.
     * @param e
     */
    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_ESCAPE:
                GameState.state = GameState.MENU;
                LevelHandler.loadMap();
                break;
            case KeyEvent.VK_S:
                if (yOffset < 5) {
                    yOffset += 1;
                }
                break;
            case KeyEvent.VK_D:
                if (xOffset < 15)
                    xOffset += 1;
                break;
            case KeyEvent.VK_W:
                if (yOffset > 0) {
                    yOffset -= 1;
                }
                break;
            case KeyEvent.VK_A:
                if (xOffset > 0)
                    xOffset -= 1;
                break;
        }
    }

    /**
     * Method used for checking if a key was released.
     * @param e
     */
    @Override
    public void keyReleased(KeyEvent e) {

    }
}
