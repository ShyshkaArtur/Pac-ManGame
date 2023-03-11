package model;

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

    public static Text text =  new Text("Score: ");;

    public static PacMan pacMan = new PacMan(32,32,20,20, Color.YELLOW);
    public static Ghosts redGhost = new Ghosts(Map.getGhostPositionX(), Map.getGhostPositionY(), 20, 20, Color.RED);

    @Override
    public void start(Stage primaryStage) throws Exception{

        Map.generateMap();
        Map.generateDots();

        Ghosts pinkGhost = new Ghosts(Map.getGhostPositionX() + 28, Map.getGhostPositionY(), Map.CELL_SIZE, Map.CELL_SIZE, Color.VIOLET);
        Ghosts orangeGhost = new Ghosts(Map.getGhostPositionX() - 28, Map.getGhostPositionY(), Map.CELL_SIZE, Map.CELL_SIZE, Color.CORAL);
        Ghosts blueGhost = new Ghosts(Map.getGhostPositionX(), Map.getGhostPositionY() - 28, Map.CELL_SIZE, Map.CELL_SIZE, Color.MEDIUMAQUAMARINE);

        PacManController.move();

        text.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        text.setX(Map.WIDTH - 140);
        text.setY(Map.HEIGHT - 23);
        text.setFill(Color.WHITE);

        root.getChildren().addAll(pacMan, redGhost, pinkGhost, orangeGhost, blueGhost, text);

        primaryStage.setTitle("Pac-Man");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
