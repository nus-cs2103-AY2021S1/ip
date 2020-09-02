package duke.gui;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

public class CircleImage {

    public static Circle createCircleImage(double circleRadius, Image image) {
        Circle background = new Circle(circleRadius);
        background.setFill(new ImagePattern(image));
        return background;
    }


}
