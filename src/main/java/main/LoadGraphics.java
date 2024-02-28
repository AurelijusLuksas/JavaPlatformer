package main;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author Aurelijus Lukšas 5 group.
 * Class used for loading all the graphics used by the game.
 */
public class LoadGraphics {
    public static final String Sky1 = "Sky1.png";
    public static final String Sky2 = "Sky2.png";
    public static final String Sky3 = "Sky3.png";
    public static final String Sky4 = "Sky4.png";
    public static final String playerAnimations = "OwletMonsterSprite.png";
    public static final String menuButtons = "MenuButtons.png";
    public static final String editorButtons = "EditorButtons.png";
    public static final String backgroundColor = "BackgroundColor.png";
    public static final String mapSprite = "PlatformerTileset.png";
    public static final String emptyTile = "emptyTile.png";
    public static final String menuBackground = "MenuBackground.png";

    /**
     * Method used for getting the image from resource folder.
     * @param fileName
     * @return
     */
    public static BufferedImage GetSpriteAtlas(String fileName) {
        BufferedImage img;
        InputStream is = LoadGraphics.class.getResourceAsStream("/" + fileName);
        try {
            assert is != null;
            img = ImageIO.read(is);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                assert is != null;
                is.close();
            } catch (IOException e) {
                throw new RuntimeException();
            }
        }
        return img;
    }

}
