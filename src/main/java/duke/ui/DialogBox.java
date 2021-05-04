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
import javafx.scene.layout.HBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

/**
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private Circle displayPicture;

    /**
     * Creates a DialogBox with the given text and image.
     *
     * @param text Text to be displayed.
     * @param img Image of person.
     */
    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        displayPicture.setFill(new ImagePattern(img));
    }

    /** Flips the dialog box such that the ImageView is on the left and text on the right. */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    /**
     * Returns a dialog box for user.
     *
     * @param text The text entered by user.
     * @param img Image of user.
     * @return DialogBox for user.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        return db;
    }

    /**
     * Returns a dialog box for Duke.
     *
     * @param text The response from Duke.
     * @param img Image of Duke.
     * @param isErrorMessage True if the message is an error message.
     * @return DialogBox for Duke.
     */
    public static DialogBox getDukeDialog(String text, Image img, boolean isErrorMessage) {
        var db = new DialogBox(text, img);
        db.flip();
        if (isErrorMessage) {
            db.setErrorDialog();
        } else {
            db.setNormalDialog();
        }
        return db;
    }

    private void setErrorDialog() {
        dialog.setStyle("-fx-background-color: #F27474; "
                + "-fx-border-color: #F27474; -fx-border-radius: 10; "
                + "-fx-border-width: 10; -fx-background-radius: 10");
    }

    private void setNormalDialog() {
        dialog.setStyle("-fx-background-color: #D1FDA4; "
                + "-fx-border-color: #D1FDA4; -fx-border-radius: 10; "
                + "-fx-border-width: 10; -fx-background-radius: 10");
    }
}
