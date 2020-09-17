package bob;

import java.io.IOException;
import java.util.Collections;

import bob.MainWindow;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

/**
 * This control represents a dialog box consisting of a circle to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {
    /**
     * A label consisting of text that comprises the dialog between user and Bob.
     */
    @FXML
    private Label dialog;

    /**
     * A circle that will display the profile picture of the user or Bob.
     */
    @FXML
    private Circle displayPicture;

    /**
     * Constructs a DialogBox that will contain response or input from the user, or Bob respectively alongside
     * profile pictures.
     *
     * @param text user input or Bob response
     * @param img profile picture of the user or Bob
     * @param path the path of the FXML file for the DialogBox
     */
    private DialogBox(String text, Image img, String path) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource(path));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Solution adapted from: https://www.youtube.com/watch?v=54fEFYx34vk
        displayPicture.setFill(new ImagePattern(img));

        dialog.setText(text);
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

    /**
     * Produces a DialogBox that will consist of user input alongside the user's profile picture.
     *
     * @param text the user input.
     * @param img the profile picture of the user.
     * @return a DialogBox that will consist of user input alongside a profile picture.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        var db = new DialogBox(text, img, "/view/DialogBoxUser.fxml");
        db.setDisplayPictureColor(Color.DODGERBLUE);
        return db;
    }

    /**
     * Produces a DialogBox that will consist of Bob's response alongside Bob's profile picture.
     *
     * @param text Bob's response to be displayed in the dialog box.
     * @param img the profile picture of Bob.
     * @return a DialogBox that will consist of Bob's response alongside a profile picture.
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img, "/view/DialogBox.fxml");
        db.flip();
        db.setDisplayPictureColor(Color.DODGERBLUE);
        return db;
    }

    /**
     * Produces a DialogBox that will consist of Bob's error message alongside Bob's profile picture.
     *
     * @param text Bob's error message.
     * @param img the profile picture of Bob.
     * @return a DialogBox that will consist of Bob's error message alongside a profile picture.
     */
    public static DialogBox getDukeDialogError(String text, Image img) {
        var db = new DialogBox(text, img, "/view/DialogBoxError.fxml");
        db.flip();
        db.setDisplayPictureColor(Color.CRIMSON);
        return db;
    }

    /**
     * Sets the display picture's border and effects to a provided color.
     *
     * @param color The color for the picture's border and effects to be set to.
     */
    public void setDisplayPictureColor(Color color) {
        displayPicture.setStroke(color);
        displayPicture.setEffect(new DropShadow(+25d, 0d, +2d, color));
    }

}