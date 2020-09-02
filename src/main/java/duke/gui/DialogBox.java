package duke.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class DialogBox extends HBox {

    private Label text;
    private ImageView displayPicture;

    /**
     * Creates a dialog box.
     */
    public DialogBox(Label l, ImageView iv) {
        this.text = l;
        this.displayPicture = iv;

        this.text.setWrapText(true);
        this.displayPicture.setFitWidth(100.0);
        this.displayPicture.setFitHeight(100.0);

        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(this.text, this.displayPicture);
    }

    /**
     * Executes a command based on the user action.
     *
     * @param text Text label.
     * @param displayPicture Image view of display picture.
     */
    public static DialogBox getUserDialog(Label text, ImageView displayPicture) {
        return new DialogBox(text, displayPicture);
    }

    /**
     * Executes a command based on the user action.
     *
     * @param text Text label.
     * @param displayPicture Image view of display picture.
     */
    public static DialogBox getDukeDialog(Label text, ImageView displayPicture) {
        var db = new DialogBox(text, displayPicture);
        db.flip();
        return db;
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
}
