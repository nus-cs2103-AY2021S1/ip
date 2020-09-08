package main.java.farrell.duke;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class UserDialog extends HBox {
    private Label textLabel;
    private ImageView displayPicture;
    private Image image = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));

    /**
     * Creates a new UserDialog containing some text.
     * @param text Text to display in the dialog.
     */
    public UserDialog(String text) {
        textLabel = new Label(text);
        displayPicture = new ImageView(image);

        textLabel.setWrapText(true);
        displayPicture.setFitWidth(100);
        displayPicture.setFitHeight(100);

        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(textLabel, displayPicture);
    }
}
