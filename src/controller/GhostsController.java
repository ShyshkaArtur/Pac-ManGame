package controller;


import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import model.Ghosts;
import model.Main;
import model.PacMan;
import view.Map;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GhostsController {

    private static Ghosts ghost = Main.blueGhost;
    private static int[][] map = Map.map;
    private static int sizeBlock = Map.CELL_SIZE;
    static Random random = new Random();

    private static int direction = 1;

    private static Timeline timeline = new Timeline(new KeyFrame(Duration.millis(200), event -> {

        if (direction == 1) { moveProcess(1); }

        else if (direction == 2) { moveProcess(2); }

        else if (direction == 3) { moveProcess(3); }

        else if (direction == 4) { moveProcess(4); }

        direction = random.nextInt(4) + 1;

    }));

    public static void move() {
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    private static void moveForwards() {

        if (direction == 1) {
            ghost.setY(ghost.getY() - sizeBlock);
        } else if (direction == 2) {
            ghost.setY(ghost.getY() + sizeBlock);
        } else if (direction == 3) {

            ghost.setX(ghost.getX() - sizeBlock);
            if (ghost.getX() < -20) {
                ghost.setX(ghost.getX() + Map.WIDTH);
            }

        } else if (direction == 4) {

            ghost.setX(ghost.getX() + sizeBlock);
            if (ghost.getX() > 582) {
                ghost.setX(2);
            }
        }
    }

    private static void moveBackwards() {

        if (direction == 1) { ghost.setY(ghost.getY() + sizeBlock); }

        else if (direction == 2) { ghost.setY(ghost.getY() - sizeBlock); }

        else if (direction == 3) { ghost.setX(ghost.getX() + sizeBlock); }

        else if (direction == 4) { ghost.setX(ghost.getX() - sizeBlock); }
    }

    private static void moveProcess(int dir) {

        endGame();
        moveForwards();

        if (checkCollisions()) {
            direction = dir;
            moveBackwards();
        }
    }

    private static boolean checkCollisions() {

        boolean isCollided = false;

        if (map[(int) (ghost.getY() / sizeBlock)][(int) (ghost.getX() / sizeBlock)] == 1) {
            isCollided = true;
        }

        return isCollided;
    }

    private static void endGame() {

        ObservableList<Node> nodes = Main.scene.getRoot().getChildrenUnmodifiable();
        List<Node> dots = new ArrayList<>();

        // Знайти всі жовті квадрати
        for (Node node : nodes) {
            if (node instanceof Rectangle && ((Rectangle) node).getFill() == Color.YELLOW) {
                dots.add(node);
            }
        }

        // Перевірити перетинання кожного квадрата з пакменом
        for (Node node : dots) {
            if (ghost.getBoundsInParent().intersects(node.getBoundsInParent())) {
                // Якщо перетинання відбувається, видалити квадрат з колекції та зі сцени
                System.exit(1);
            }
        }
    }


}
