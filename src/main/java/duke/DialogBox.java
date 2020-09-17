package duke;

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
    /** Label containing the text to be displayed in the dialog box. */
    @FXML
    private Label dialog;
    /** ImageView to represent the display picture for either the user or Duke. */
    @FXML
    private ImageView displayPicture;

    /**
     * Creates and initialises a new Dialog box with a Label containing the
     * text and an Image representing a display picture.
     *
     * @param text Text to be displayed in the dialog box.
     * @param img Image to be displayed in the dialog box.
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
     * Retrieves the dialog box containing the user's image and a label containing the user text.
     *
     * @param text User text.
     * @param img Image representing the user.
     * @return Dialog box representing the user's response.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        DialogBox dialogBox = new DialogBox(text, img);
        String userDialogStyle = "-fx-background-color: salmon; "
                + "-fx-background-radius: 10; -fx-padding: 10 10 10 10";
        dialogBox.dialog.setStyle(userDialogStyle);
        dialogBox.displayPicture.setClip(new Circle(34, 37, 34));
        return dialogBox;
    }

    /**
     * Retrieves the dialog box containing Duke's image and a label containing Duke's text.
     *
     * @param text Duke's text.
     * @param img Image representing Duke.
     * @return Dialog box representing Duke's response.
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        String dukeDialogStyle = "-fx-background-color: lightSteelBlue; "
                + "-fx-background-radius: 10; -fx-padding: 10 10 10 10";
        db.dialog.setStyle(dukeDialogStyle);
        db.displayPicture.setClip(new Circle(35, 35, 34));
        db.flip();
        return db;
    }
}
