package duke.gui;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

/**
 * A class for creating images with a circular frame.
 * The class provides a single static method that creates a circular image from an image and a specified radius.
 * To be used with JavaFX.
 */
public class CircleImage {

    /**
     * Returns a JavaFX node of the image with a circular frame.
     *
     * @param circleRadius radius of the circle.
     * @param image image to be used to fill the circle.
     * @return circle with the provided radius and image as fill.
     */
    public static Circle createCircleImage(double circleRadius, Image image) {
        Circle background = new Circle(circleRadius);
        background.setFill(new ImagePattern(image));
        return background;
    }


}
