package duke.ui;

import java.io.IOException;
import java.util.Collections;

import duke.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face
 * and a label containing text from the speaker.
 *
 * Display pictures taken from: https://www.veryicon.com/icons/movie--tv/doraemon/
 * Background image taken from: https://tenor.com/view/yay-hooray-party-celebrate-parties-gif-13284604
 * Images used are of "License: Free for personal and commercial purpose".
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    /**
     * Class constructor.
     *
     * @param text The text string.
     * @param img The Image.
     */
    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    /**
     * Returns a user dialog.
     *
     * @param text The message.
     * @param img The image.
     * @return User dialog.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        DialogBox db = new DialogBox(text, img);
        db.setPadding(new Insets(10, 10, 10, 10));
        return db;
    }

    /**
     * Returns a Doraemon dialog.
     *
     * @param text The message.
     * @param img The image.
     * @return Doraemon dialog.
     */
    public static DialogBox getDoraemonDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        db.setPadding(new Insets(10, 10, 10, 10));
        return db;
    }
}
