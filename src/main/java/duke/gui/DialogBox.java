package duke.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;

public class DialogBox extends HBox {

    private static final double IMAGE_RADIUS = 35.0;

    private TextBox text;
    private Circle displayPicture;

    private DialogBox(String text, Image image, boolean left) {
        this.text = left
                ? TextBox.leftwardTextBox(text)
                : TextBox.rightwardTextBox(text);
        displayPicture = CircleImage.createCircleImage(IMAGE_RADIUS, image);

        setAlignment(Pos.TOP_RIGHT);
        getChildren().addAll(this.text, displayPicture);

        setSpacing(10.0);
    }
    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    public static DialogBox getUserDialog(String userMessage, Image image) {
        return new DialogBox(userMessage, image, false);
    }

    public static DialogBox getDukeDialog(String dukeMessage, Image image) {
        DialogBox box = new DialogBox(dukeMessage, image, true);
        box.flip();
        return box;
    }


}
