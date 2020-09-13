package duke.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class DialogBox extends HBox {

    private Label text;
    private ImageView displayPicture;

    /**
     * Represents a DialogBox that will be displayed in the GUI
     * @param l the text to be displayed in the container
     * @param iv the image to be displayed in the container
     */
    public DialogBox(Label l, ImageView iv) {
        text = l;
        text.setWrapText(true);

        iv.setClip(new Circle(60, 60, 60));
        displayPicture = iv;
        displayPicture.setFitWidth(120.0);
        displayPicture.setFitHeight(120.0);

        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(text, displayPicture);
        this.setSpacing(20);
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

    public static DialogBox getUserDialog(Label l, ImageView iv) {
        l.setTextFill(Color.web("#08d9d6"));
        DialogBox userDialog = new DialogBox(l, iv);
        userDialog.setId("HBoxUser");
        userDialog.getStylesheets().add("/css/styles.css");
        return userDialog;
    }

    public static DialogBox getDukeDialog(Label l, ImageView iv) {
        l.setTextFill(Color.web("#ff2e63"));
        var db = new DialogBox(l, iv);
        db.setId("HBoxDuke");
        db.getStylesheets().add("/css/styles.css");
        db.flip();
        return db;
    }
}
