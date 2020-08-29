package main.gui;

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

    private DialogBox(Label text, ImageView displayPicture) {
        this.text = text;
        this.displayPicture = displayPicture;

        text.setWrapText(true);
        displayPicture.setFitWidth(100.0);
        displayPicture.setFitHeight(100.0);

        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(text, displayPicture);
    }

    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    /**
     * Constructs a DialogBox instance with dialog and display picture of user
     * @param text the dialog
     * @param displayPicture the picture of the person speaking
     */
    public static DialogBox getUserDialog(Label text, ImageView displayPicture) {
        return new DialogBox(text, displayPicture);
    }

    /**
     * Constructs a DialogBox instance with dialog and display picture of Duke
     * @param text the dialog
     * @param displayPicture the picture of the person speaking
     */
    public static DialogBox getDukeDialog(Label text, ImageView displayPicture) {
        DialogBox db = new DialogBox(text, displayPicture);
        db.flip();
        return db;
    }
}
