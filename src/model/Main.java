package model;

import controller.GhostsController;
import controller.PacManController;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import view.Map;


public class Main extends Application {

    public static Group root = new Group();
    public static Scene scene = new Scene(root, Map.WIDTH, Map.HEIGHT, Color.BLACK);

    public static Ghosts blueGhost = new Ghosts(Map.getGhostPositionX(), Map.getGhostPositionY(), 20, 20, Color.MEDIUMAQUAMARINE);

    public static Text text =  new Text("Score: ");;

    @Override
    public void start(Stage primaryStage) throws Exception {

        Map.generateMap();
        Map.generateDots();
        Map.drawPacMan();

        Map.addLives(45, Map.HEIGHT - 40);
        Map.addLives(79, Map.HEIGHT - 40);
        Map.addLives(113, Map.HEIGHT - 40);

        PacManController.move();
        GhostsController.move();


        text.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        text.setX(Map.WIDTH - 170);
        text.setY(Map.HEIGHT - 23);
        text.setFill(Color.WHITE);

        root.getChildren().addAll( blueGhost, text);

        primaryStage.setTitle("Pac-Man");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();


    }

    public static void main(String[] args) {
        launch(args);
    }
}
