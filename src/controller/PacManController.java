package controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import model.Main;
import model.PacMan;
import view.Map;

import java.util.ArrayList;
import java.util.List;

public class PacManController {

    private static PacMan pacMan = Map.pacMan;
    private static Scene scene = Main.scene;
    private static int[][] map = Map.map;
    private static int sizeBlock = Map.CELL_SIZE;

    private static int direction = 1;
    private static int nextDirection = 1;
    private static int score = 0; // очки

    public static void move() {
        timeline.setCycleCount(Animation.INDEFINITE);

        scene.setOnKeyPressed(event -> {
            // Переміщуємо пакмена у залежності від клавіші, що була натиснута
            KeyCode keyCode = event.getCode();
            switch (keyCode) {

                case UP:
                    nextDirection = 1;
                    timeline.play();
                    break;

                case DOWN:
                    nextDirection = 2;
                    timeline.play();
                    break;

                case LEFT:
                    nextDirection = 3;
                    timeline.play();
                    break;

                case RIGHT:
                    nextDirection = 4;
                    timeline.play();
                    break;

                case SPACE:
                    timeline.stop();
                    break;
            }
        });
    }

    private static Timeline timeline = new Timeline(new KeyFrame(Duration.millis(200), event -> {

        if (direction == 1) { moveProcess(1); }

        else if (direction == 2) { moveProcess(2); }

        else if (direction == 3) { moveProcess(3); }

        else if (direction == 4) { moveProcess(4); }

    }));

    private static void moveProcess(int dir) {

        eatDots();
        changeDirectionIfPossible();
        moveForwards();

        if (checkCollisions()) {
            direction = dir;
            moveBackwards();
        }
    }

    private static void moveForwards() {

        if (direction == 1) { pacMan.setY(pacMan.getY() - sizeBlock); }

        else if (direction == 2) { pacMan.setY(pacMan.getY() + sizeBlock); }

        else if (direction == 3) {
            pacMan.setX(pacMan.getX() - sizeBlock);

            if (pacMan.getX() < -20) { pacMan.setX(pacMan.getX() + Map.WIDTH); }

        } else if (direction == 4) {
            pacMan.setX(pacMan.getX() + sizeBlock);

            if (pacMan.getX() > 582) { pacMan.setX(2); }
        }
    }

    private static void moveBackwards() {

        if (direction == 1) { pacMan.setY(pacMan.getY() + sizeBlock); }

        else if (direction == 2) { pacMan.setY(pacMan.getY() - sizeBlock); }

        else if (direction == 3) { pacMan.setX(pacMan.getX() + sizeBlock); }

        else if (direction == 4) { pacMan.setX(pacMan.getX() - sizeBlock); }
    }

    private static void changeDirectionIfPossible() {

        if (direction == nextDirection) { return; }

        int tempDirection = direction;
        direction = nextDirection;
        moveForwards();

        if (checkCollisions()) {
            moveBackwards();
            direction = tempDirection;
        } else {
            moveBackwards();
        }
    }

    private static boolean checkCollisions() {

        boolean isCollided = false;

        if (map[(int) (pacMan.getY() / sizeBlock)][(int) (pacMan.getX() / sizeBlock)] == 1) {
            isCollided = true;
        }

        return isCollided;
    }

    private static void eatDots() {

        ObservableList<Node> nodes = Main.scene.getRoot().getChildrenUnmodifiable();
        List<Node> dots = new ArrayList<>();

        // Знайти всі жовті квадрати
        for (Node node : nodes) {
            if (node instanceof Rectangle && ((Rectangle) node).getFill() == Color.ORANGE) {
                dots.add(node);
            }
        }

        // Перевірити перетинання кожного квадрата з пакменом
        for (Node node : dots) {
            if (pacMan.getBoundsInParent().intersects(node.getBoundsInParent())) {
                // Якщо перетинання відбувається, видалити квадрат з колекції та зі сцени
                Main.root.getChildren().remove(node);
                score += 5;
                Main.text.setText("Score:  " + score);
            }
        }
    }
}
