package duke.controllers;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.shape.Circle;

/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {
    private static final double SIZE = 99;
    private static final double RADIUS = SIZE / 2;

    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    private Circle clip = new Circle();

    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        clip.setRadius(RADIUS);
        clip.setCenterX(displayPicture.getX() + RADIUS);
        clip.setCenterY(displayPicture.getY() + RADIUS);
        displayPicture.setClip(clip);

        dialog.setText(text);
        displayPicture.setImage(img);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    public static DialogBox getUserDialog(String text, Image img) {
        DialogBox userDialog = new DialogBox(text, img);
        userDialog.dialog.setStyle("-fx-background-color: #afeeee"); // blue
        userDialog.setStyle("-fx-alignment: CENTER_RIGHT");
        userDialog.dialog.setMinHeight(Region.USE_PREF_SIZE);
        return userDialog;
    }

    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.dialog.setStyle("-fx-background-color: #e6e6ff"); // purple
        db.dialog.setMinHeight(Region.USE_PREF_SIZE);
        db.flip();
        db.setStyle("-fx-alignment: CENTER_LEFT");
        return db;
    }
}
