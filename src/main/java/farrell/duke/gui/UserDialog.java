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
import javafx.scene.text.TextAlignment;

public class UserDialog extends HBox {
    private Label textLabel;
    private Circle displayPicture;
    private Image image = new Image(this.getClass().getResourceAsStream("/images/chick.png"));

    /**
     * Creates a new UserDialog containing some text.
     * @param text Text to display in the dialog.
     */
    public UserDialog(String text) {
        this.setSpacing(10);
        this.setPadding(new Insets(5));

        displayPicture = new Circle(50);
        displayPicture.setFill(new ImagePattern(image));

        textLabel = new Label(text);
        textLabel.setWrapText(true);
        textLabel.setPrefSize(255, 100);
        textLabel.setPadding(new Insets(5));
        textLabel.setTextAlignment(TextAlignment.RIGHT);
        textLabel.setAlignment(Pos.CENTER_RIGHT);

        BackgroundFill textBg = new BackgroundFill(
                Color.BISQUE,
                new CornerRadii(10),
                null
        );
        textLabel.setBackground(new Background(textBg));

        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(textLabel, displayPicture);
    }
}
