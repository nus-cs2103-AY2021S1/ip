package main.java.farrell.duke.gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

/** Encapsulates a set of UI elements used to show program messages. */
public class DukeDialog extends HBox {
    private Label textLabel;
    private Circle displayPicture;
    private Image image = new Image(this.getClass().getResourceAsStream("/images/chicken.png"));

    /**
     * Creates a new DukeDialog containing some text.
     * @param text Text to display in the dialog.
     */
    public DukeDialog(String text) {
        this.setSpacing(10);
        this.setPadding(new Insets(5));

        displayPicture = new Circle(50);
        displayPicture.setFill(new ImagePattern(image));

        textLabel = new Label(text);
        textLabel.setWrapText(true);
        textLabel.setMinSize(255, 100);
        textLabel.setPadding(new Insets(5));

        BackgroundFill textBg = new BackgroundFill(
                Color.BISQUE,
                new CornerRadii(10),
                null
        );
        textLabel.setBackground(new Background(textBg));

        this.setAlignment(Pos.TOP_LEFT);
        this.getChildren().addAll(displayPicture, textLabel);
    }
}
