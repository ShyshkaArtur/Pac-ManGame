package model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Wall extends Rectangle {

    public Wall(double x, double y, double width, double height) {
        super(x, y, width, height);
        setFill(Color.DARKBLUE);
    }
}
