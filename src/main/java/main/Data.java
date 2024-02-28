package main;

/**
 * @author Aurelijus Lukšas 5 group.
 * Class used for storing data which is constant and not going to change.
 * Can be used to easily change some parameters.
 */
public class Data {
    /**
     * Data of the buttons.
     */
    public static class Buttons {
        /**
         * Data of the menu buttons.
         */
        public static class Menu {
            public static final int Start = 0;
            public static final int Custom = 1;
            public static final int Options = 2;
            public static final int Quit = 3;
        }

        /**
         * Data of the map editor buttons.
         */
        public static class Editor {
            public static final int Save = 0;
            public static final int Load = 1;
            public static final int Layer1 = 2;
            public static final int Layer2 = 3;
            public static final int Layer3 = 4;
        }
    }

    /**
     * Data of the player.
     * Includes Animation data.
     * Includes Player state.
     */
    public static class PlayerData {
        public static boolean UP = false;
        public static boolean LEFT = true;
        public static boolean DOWN = false;
        public static boolean RIGHT = false;

        public static final int IdleR = 0;
        public static final int IdleL = 1;
        public static final int WalkR = 2;
        public static final int WalkL = 3;
        public static final int RunR = 4;
        public static final int RunL = 5;
        public static final int JumpR = 6;
        public static final int JumpL = 7;
        public static final int HurtR = 8;
        public static final int HurtL = 9;
        public static final int DeathR = 10;
        public static final int DeathL = 11;
        public static final int Attack1R = 12;
        public static final int Attack1L = 13;
        public static final int Attack2R = 14;
        public static final int Attack2L = 15;
        public static final int Climb = 16;
        public static final int ThrowR = 17;
        public static final int ThrowL = 18;
        public static final int PushR = 19;
        public static final int PushL = 20;
        public static final int WalkAttackR = 21;
        public static final int WalkAttackL = 22;
        public static final int RunDustR = 23;
        public static final int RunDustL = 24;
        public static final int JumpDust = 25;

        public static final int PlayerHeight = 32;
        public static final int PlayerWidth = 32;

        /**
         * Method used for getting the amount of sprites a player animation has.
         * @param playerAction
         * @return
         */
        public static int spriteAmount(int playerAction) {
            switch (playerAction) {
                case IdleL:
                case IdleR:
                    return 4;
                case WalkL:
                case WalkR:
                    return 6;
                case RunL:
                case RunR:
                    return 6;
                case JumpL:
                case JumpR:
                    return 8;
                case HurtL:
                case HurtR:
                    return 4;
                case DeathL:
                case DeathR:
                    return 8;
                case Attack1L:
                case Attack1R:
                    return 4;
                case Attack2L:
                case Attack2R:
                    return 6;
                case Climb:
                    return 4;
                case ThrowL:
                case ThrowR:
                    return 4;
                case PushL:
                case PushR:
                    return 6;
                case WalkAttackL:
                case WalkAttackR:
                case RunDustL:
                case RunDustR:
                    return 6;
                case JumpDust:
                    return 5;
            }
            return 0;
        }
    }

    /**
     * Data of the direction which player is facing.
     */
    public static class Directions {
        public static final int UP = 0;
        public static final int DOWN = 1;
        public static final int LEFT = 2;
        public static final int RIGHT = 3;
    }

    /**
     * Data of the Display.
     */
    public static class Display {

        public static final float GameScale = 1f;
        public static final int MapHeight = 20;
        public static final int MapWidth = 40;
        public static final int TilesCountX = 30;
        public static final int TilesCountY = 20;
        public static final int TilesOnScreen = TilesCountX * TilesCountY;
        public static final int TilesInMap = MapHeight * MapWidth;
        public static final int TilesWidth = 32;
        public static final int LeftBorder = (int)(0.4 * TilesCountX * TilesWidth);
        public static final int RightBorder = (int)(0.6 * TilesCountX * TilesWidth);
        public static final int maxTilesOffsetX = MapWidth - TilesCountX;
        public static final int maxLevelOffsetX = maxTilesOffsetX * TilesWidth;
        public static final int ScreenWidth = TilesCountX * TilesWidth;
        public static final int ScreenHeight = TilesCountY * TilesWidth;

    }

}
