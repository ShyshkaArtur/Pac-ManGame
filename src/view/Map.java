package view;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import model.Ghosts;
import model.Main;
import model.PacMan;

public class Map {

    public static int[][] map = {
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1},
            {1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 0, 1},
            {1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1},
            {1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1},
            {1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1},
            {1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 2, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1},
            {0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 2, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0},
            {1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 2, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1},
            {1, 0, 0, 0, 0, 0, 1, 0, 0, 1, 2, 1, 0, 0, 1, 0, 0, 0, 0, 0, 1},
            {1, 0, 1, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 1, 1, 0, 1},
            {1, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1},
            {1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1},
            {1, 1, 0, 0, 1, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1, 0, 0, 1, 1},
            {1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1},
            {1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
    };

    private static Group root = Main.root;

    public static final int CELL_SIZE = 28; // розмір однієї клітинки
    public static final int WIDTH = map[0].length * CELL_SIZE; // ширина сцени
    public static final int HEIGHT = map.length * CELL_SIZE + 56; // висота сцени

    private static final int GHOST_POSITION_X = (WIDTH / 2 - CELL_SIZE / 2) + 4; // позиція привида на осі X
    private static final int GHOST_POSITION_Y = ((HEIGHT / 2) - CELL_SIZE - (CELL_SIZE / 2)) - 24; // позиція привида на осі Y

    public static Rectangle lives;
    public static PacMan pacMan = new PacMan(30,30,24,24, Color.YELLOW);

    public static int getGhostPositionX() {
        return GHOST_POSITION_X;
    }

    public static int getGhostPositionY() {
        return GHOST_POSITION_Y;
    }

    public static void generateMap() {
        for (int row = 0; row < Map.map.length; row++) {
            for (int col = 0; col < Map.map[0].length; col++) {
                if (Map.map[row][col] == 1) {
                    Rectangle rect = new Rectangle(col * CELL_SIZE + 3, row * CELL_SIZE + 3, 22, 22);
                    rect.setStroke(Color.DARKBLUE);
                    rect.setStrokeWidth(5);
                    Main.root.getChildren().addAll(rect);
                }
            }
        }
    }

    public static void generateDots() {
        for (int row = 0; row < map.length; row++) {
            for (int col = 0; col < map[0].length; col++) {
                if (map[row][col] == 0) {
                    Rectangle rect = new Rectangle(col * CELL_SIZE + 11, row * CELL_SIZE + 11, 5, 5);
                    rect.setFill(Color.ORANGE);
                    Main.root.getChildren().addAll(rect);
                }
            }
        }
    }

    public static void drawPacMan() {
        pacMan.setArcHeight(28);
        pacMan.setArcWidth(28);
        root.getChildren().add(pacMan);
    }

    public static void addLives(int x, int y) {
        lives = new Rectangle(x, y, 24, 24);
        lives.setArcHeight(24);
        lives.setArcWidth(24);
        lives.setFill(Color.YELLOW);
        root.getChildren().add(lives);
    }
}
