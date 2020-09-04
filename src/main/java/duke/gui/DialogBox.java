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

    private static final double IMAGE_RADIUS = 50.0;

    private Label text;
    private Circle displayPicture;

    public DialogBox(String text, Image image) {
        this.text = new Label(text);
        displayPicture = CircleImage.createCircleImage(IMAGE_RADIUS, image);

        this.text.setWrapText(true);

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
        return new DialogBox(userMessage, image);
    }

    public static DialogBox getDukeDialog(String dukeMessage, Image image) {
        var db = new DialogBox(dukeMessage, image);
        db.flip();
        return db;
    }


}
