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

    public DialogBox(Label label, Image image) {
        text = label;
        displayPicture = CircleImage.createCircleImage(IMAGE_RADIUS, image);

        text.setWrapText(true);

        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(text, displayPicture);
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

    public static DialogBox getUserDialog(Label l, Image iv) {
        return new DialogBox(l, iv);
    }

    public static DialogBox getDukeDialog(Label l, Image iv) {
        var db = new DialogBox(l, iv);
        db.flip();
        return db;
    }


}
