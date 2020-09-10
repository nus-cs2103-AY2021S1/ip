package duke.ui.visualui;

import static duke.util.Keyword.KEYWORD_DUKE;
import static duke.util.Keyword.KEYWORD_USER;

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
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;

/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {
    private static final String USER_SPEECH_BUBBLE = "-fx-background-radius: 12 12 0 12;";
    private static final String DUKE_SPEECH_BUBBLE = "-fx-background-radius: 12 12 12 0;";
    private static final int IMAGE_SIZE = 80;
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;
    @FXML
    private VBox speechBubble;

    private DialogBox(String text, Image img, String user) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        setProperties(text, img, user);
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
        return new DialogBox(text, img, KEYWORD_USER);
    }

    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img, KEYWORD_DUKE);
        db.flip();
        return db;
    }

    /**
     * Set the design of the speech bubble.
     *
     * @param text The message that is being displayed
     * @param img The person image.
     * @param user The name of the user.
     */
    private void setProperties(String text, Image img, String user) {
        dialog.setText(text);
        dialog.getStylesheets().add("view/DialogBox.css");
        setSpeechBubbleStyle(user);
        speechBubble.getStylesheets().add("view/DialogBox.css");
        Rectangle clip = new Rectangle(displayPicture.getFitWidth(), displayPicture.getFitHeight());
        clip.setArcWidth(IMAGE_SIZE);
        clip.setArcHeight(IMAGE_SIZE);
        displayPicture.setClip(clip);
        displayPicture.setImage(img);
    }

    /**
     * Depending on the user, it will show a speech bubble orientated from the left or right.
     *
     * @param user The person speaking.
     */
    private void setSpeechBubbleStyle(String user) {
        if (user.equals(KEYWORD_USER)) {
            speechBubble.setStyle(USER_SPEECH_BUBBLE);
        } else {
            speechBubble.setStyle(DUKE_SPEECH_BUBBLE);
        }
    }
}
