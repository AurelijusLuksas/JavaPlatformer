package main;

import java.awt.*;
import java.awt.image.BufferedImage;

import static main.Data.Display.*;
import static main.Colisions.*;
import static main.Data.PlayerData.*;

/**
 * @author Aurelijus Lukšas 5 group.
 * Player class.
 * Contains player information and physics and other nescesarry things.
 */
public class Player extends Entity {

    private boolean left, right, jump, mouse1, mouse2;
    private BufferedImage[][] animations;
    private BufferedImage aniSprite;
    private int playerAction = IdleR;
    private int aniTick, aniIndex;
    private boolean isMoving = false;
    private float speed = 1f;
    private static int[][] levelData;
    private final float xOffset = 16, yOffset = 22;

    private float airSpeed = 0f;
    private final float gravity = 0.05f;
    private float jumpPower = -3f;
    private final float fallCollision = 0.5f;
    private boolean isInAir = false;
    private float xSpeed = 0;
    private boolean isDead = false;

    /**
     * Constructor used for starting the player.
     * @param x
     * @param y
     * @param width
     * @param height
     */
    public Player(float x, float y, int width, int height) {
        super(x, y, width, height);
        aniSprite = LoadGraphics.GetSpriteAtlas(LoadGraphics.playerAnimations);
        getAnimations();
        createHitbox(x, y, 15, 25);
        isInAir = true;
    }

    /**
     * Method used for setting the right player action at the right moment.
     */
    private void setAnimations() {
        int startAni = playerAction;
        if (!isDead) {
            if (mouse1 && !isMoving) {
                if (RIGHT) {
                    playerAction = Attack1R;
                    mouse1 = false;
                } else
                    playerAction = Attack1L;
            }

            if (isMoving) {
                if (left && !right) {
                    playerAction = WalkL;
                } else if (right && !left) {
                    playerAction = WalkR;
                } else if (RIGHT && xSpeed == 0) {
                    playerAction = IdleR;
                } else if (LEFT && xSpeed == 0) {
                    playerAction = IdleL;
                }
            } else {
                if (RIGHT) {
                    playerAction = IdleR;
                } else {
                    playerAction = IdleL;
                }
            }

            if (isInAir) {
                if (left && !right) {
                    playerAction = JumpL;
                } else if (right && !left) {
                    playerAction = JumpR;
                }
            }

            if (startAni != playerAction) {
                resetAniTick();
            }
        } else {
            speed = 0f;
            jumpPower = 0f;
            if (RIGHT)
                playerAction = DeathR;
            if (LEFT)
                playerAction = DeathL;
        }

    }

    /**
     * Method used for resetting the player animation loop.
     */
    private void resetAniTick() {
        aniTick = 0;
        aniIndex = 0;
    }

    /**
     * Method used for getting the player animation array from png file.
     */
    private void getAnimations() {
        animations = new BufferedImage[26][8];
        for(int j = 0; j < animations.length; j++) {
            for (int i = 0; i < animations[j].length; i++) {
                animations[j][i] = aniSprite.getSubimage(i * 32, j * 32, 32, 32);
            }
        }

    }

    /**
     * Method used for updating player animation loop and speed.
     */
    private void updateAniTick() {
        aniTick++;
        int aniSpeed = 30;
        if (aniTick >= aniSpeed) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= Data.PlayerData.spriteAmount(playerAction)) {
                aniIndex = 0;
                if (hitbox.y == 614) {
                    resetPlayerPos();
                    isDead = false;
                    speed = 1f;
                    jumpPower = -3f;
                }
            }
        }
    }


    /**
     * Method used for loading level data to player class.
     * @param data
     */
    public void loadData(int[][] data) {
        levelData = data;
    }

    /**
     * Method used for calling player methods which need to be updated by the game loop.
     */
    public void update() {
        updatePosition();
        updateAniTick();
        setAnimations();
    }

    /**
     * Method used for resetting player position.
     */
    public void resetPlayerPos() {
        hitbox.x = LevelHandler.startX;
        hitbox.y = LevelHandler.startY;
        isInAir = true;
    }

    /**
     * Method used for rendering player.
     * @param g
     * @param levelOffsetX
     */
    public void render(Graphics g, int levelOffsetX) {
        g.drawImage(animations[playerAction][aniIndex], (int)(hitbox.x  - xOffset) - levelOffsetX, (int)(hitbox.y - yOffset),48,48, null);
//        drawHitbox(g, levelOffsetX);
    }

    /**
     * Method used for calculating and updating player position.
     */
    private void updatePosition() {
        isMoving = false;

        if (hitbox.y == 614) {
            isDead = true;
        }

        if(jump) {
            jump();
        }
        if(!left && !right && !isInAir) {
            return;
        }
        float xSpeed = 0;
        if (right && ! left) {
            RIGHT = true;
            LEFT = false;
        }
        if (left && !right) {
            LEFT = true;
            RIGHT = false;
        }
        if(right && !left) {
            xSpeed += speed;
        }else if (left && !right) {
            xSpeed -= speed;
        }
        if(!isInAir) {
            if(!EntityOnGround(hitbox, levelData)) {
                isInAir = true;
            }
        }

        if(isInAir) {
            if(canMove(hitbox.x, hitbox.y + airSpeed, hitbox.width, hitbox.height, levelData)) {
               hitbox.y += airSpeed;
               airSpeed += gravity;
               updateX(xSpeed);
            }else {
                hitbox.y = GetYPositionCollision(hitbox, airSpeed);
                if(airSpeed > 0)
                    resetInAir();
                else
                    airSpeed = fallCollision;
                updateX(xSpeed);
            }


        }else
            updateX(xSpeed);

        isMoving = true;
    }

    /**
     * Method used for checking if the player can jump and setting the value of jump to true;
     */
    private void jump() {
        if(isInAir)
            return;
        isInAir = true;
        airSpeed = jumpPower;
    }

    /**
     * Method used for checking if the player is in air when it should be and resetting it.
     */
    private void resetInAir() {
        isInAir = false;
        airSpeed = 0;
    }

    /**
     * Method used for updating player X position.
     * @param xSpeed
     */
    private void updateX(float xSpeed) {
        if(canMove(hitbox.x + xSpeed, hitbox.y, hitbox.width, hitbox.height, levelData)) {
            hitbox.x += xSpeed;
        }else {
            hitbox.x = GetXCollision(hitbox, xSpeed);
        }
    }

    /**
     * Left setter.
     * @param left
     */
    public void setLeft(boolean left) {
        this.left = left;
    }

    /**
     * Right setter.
     * @param right
     */
    public void setRight(boolean right) {
        this.right = right;
    }

    /**
     * Jump setter
     * @param jump
     */
    public void setJump(boolean jump) {
        this.jump = jump;
    }
}

