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

    private static double rectangleSpeed = 1;// швидкість пакмена
    private static int score = 0; // очки

    private static PacMan pacMan = Main.pacMan;
    private static Scene scene = Main.scene;

    public static void move() {

        // Створити Timeline

        Timeline timelineUp = new Timeline(new KeyFrame(Duration.millis(10), event -> {
            pacMan.setY(pacMan.getY() - rectangleSpeed);
            if (checkCollision()) {
                pacMan.setY(pacMan.getY() + rectangleSpeed);
            }
        }));
        timelineUp.setCycleCount(Animation.INDEFINITE);

        Timeline timelineDown = new Timeline(new KeyFrame(Duration.millis(10), event -> {
            pacMan.setY(pacMan.getY() + rectangleSpeed);
            if (checkCollision()) {
                pacMan.setY(pacMan.getY() - rectangleSpeed);
            }
        }));
        timelineDown.setCycleCount(Animation.INDEFINITE);

        Timeline timelineRight = new Timeline(new KeyFrame(Duration.millis(10), event -> {
            pacMan.setX(pacMan.getX() + rectangleSpeed);
            if(pacMan.getX() > 582) {
                pacMan.setX(4);
            }
            if (checkCollision()) {
                pacMan.setX(pacMan.getX() - rectangleSpeed);
            }
        }));
        timelineRight.setCycleCount(Animation.INDEFINITE);

        Timeline timelineLeft = new Timeline(new KeyFrame(Duration.millis(10), event -> {
            pacMan.setX(pacMan.getX() - rectangleSpeed);
            if(pacMan.getX() < (-20)) {
                pacMan.setX(pacMan.getX() + Map.WIDTH);
            }
            if (checkCollision()) {
                pacMan.setX(pacMan.getX() + rectangleSpeed);
            }
        }));
        timelineLeft.setCycleCount(Animation.INDEFINITE);

        // Викликати метод play() на Timeline, коли клавіша була натиснута
        scene.setOnKeyPressed(event -> {
            // Переміщуємо пакмена у залежності від клавіші, що була натиснута
            KeyCode keyCode = event.getCode();
            switch (keyCode) {

                case UP:
                    timelineLeft.stop();
                    timelineRight.stop();
                    timelineDown.stop();
                    timelineUp.play();
                    break;

                case DOWN:
                    timelineLeft.stop();
                    timelineRight.stop();
                    timelineUp.stop();
                    timelineDown.play();
                    break;

                case LEFT:
                    timelineUp.stop();
                    timelineDown.stop();
                    timelineRight.stop();
                    timelineLeft.play();
                    break;

                case RIGHT:
                    timelineUp.stop();
                    timelineDown.stop();
                    timelineLeft.stop();
                    timelineRight.play();
                    break;

                case SPACE:
                    timelineUp.stop();
                    timelineDown.stop();
                    timelineLeft.stop();
                    timelineRight.stop();
                    break;
            }
        });
    }


    private static boolean checkCollision() {
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
                score += 2;
                Main.root.getChildren().remove(node);
                Main.text.setText("Score:  " + score);
            }
        }

        // Перевірити перетинання з іншими об'єктами
        for (Node node : nodes) {
            if (node != pacMan && node.getBoundsInParent().intersects(pacMan.getBoundsInParent())) {
                return true;
            }
        }

        return false;
    }
}
