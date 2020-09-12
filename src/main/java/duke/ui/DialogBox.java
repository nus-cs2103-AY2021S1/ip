package duke.ui;

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
import javafx.scene.shape.Circle;

/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {
    private static final String USER_BACKGROUND =
            "-fx-background-color: rgba(114, 205, 247, 0.5); -fx-background-radius: 15;";
    private static final String DUKE_BACKGROUND =
            "-fx-background-color: rgba(255, 168, 220, 0.5); -fx-background-radius: 15;";
    private static final String ERROR_STYLE =
            "-fx-background-color: rgba(242, 66, 54, 0.5); -fx-background-radius: 15;"
                    + "-fx-font-size: 16px; -fx-font-weight: bold; -fx-color: red;";
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    private DialogBox(String text, Image img, boolean isErrorMessage, boolean isDukeResponse) {
        super(10);
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        // Sets different background depending on nature of message
        if (isDukeResponse) {
            dialog.setStyle(DUKE_BACKGROUND);
        } else {
            dialog.setStyle(USER_BACKGROUND);
        }
        if (isErrorMessage) {
            dialog.setStyle(ERROR_STYLE);
        }

        displayPicture.setImage(img);
        final Circle clip = new Circle(50, 50, 50);
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

    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img, false, false);
    }

    public static DialogBox getDukeDialog(String text, Image img, boolean isErrorMessage) {
        var db = new DialogBox(text, img, isErrorMessage, true);
        db.flip();
        return db;
    }
}
