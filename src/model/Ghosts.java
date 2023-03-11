package model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Ghosts extends Rectangle{

    public Ghosts(int x, int y, int width, int height, Color fill) {
        super(x, y, width, height);
        setFill(fill);
    }

}
