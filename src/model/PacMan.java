package model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class PacMan extends Rectangle {

    public PacMan(int x, int y, int width, int height, Color fill) {
        super(x, y, width, height);
        this.setArcHeight(height);
        this.setArcWidth(width);
        setFill(fill);
    }

}
