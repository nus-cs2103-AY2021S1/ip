package duke.application;

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
import javafx.scene.shape.Rectangle;

/**
 * Dialog Box used for the main app.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    /**
     * DialogBox constructor.
     *
     * @param text The input of the user or reply by the bot.
     * @param img  The image of the bot/user.
     */
    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
            clipToCircle();
            dialog.setText(text);
            displayPicture.setImage(img);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Clip the display picture to circle.
     */
    private void clipToCircle() {
        Rectangle clip = new Rectangle();
        clip.setWidth(99);
        clip.setHeight(99);
        clip.setArcHeight(99);
        clip.setArcWidth(99);
        displayPicture.setClip(clip);
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
     * Creates a DialogBox from the user input.
     *
     * @param text User input.
     * @param img  User display picture.
     * @return Return a user DialogBox.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    /**
     * Creates a DialogBox from the bot's response.
     *
     * @param text Bot response.
     * @param img  Bot's display picture.
     * @return Return a bot DialogBox.
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        return db;
    }
}
