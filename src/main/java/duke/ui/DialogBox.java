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

/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {
    private static final String DIALOG_BOX_STYLE = "-fx-border-color: black; -fx-border-radius: 10px;"
            + "-fx-background-radius: 10px; -fx-min-height: 30px; -fx-padding: 5px; -fx-font-weight: bold; ";
    private static final String USER_DIALOG_COLOR = "-fx-background-color: blanchedalmond";
    private static final String DUKE_DIALOG_COLOR = "-fx-background-color: lightpink";

    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    /**
     * Constructs a DialogBox object that handles the user interface of the program.
     *
     * @param text Text to be displayed.
     * @param img Image displayed of either the user or "Bob".
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

    private static String setDialogColor(String dialogColor) {
        return DIALOG_BOX_STYLE + dialogColor;
    }

    public static DialogBox getUserDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.dialog.setStyle(setDialogColor(USER_DIALOG_COLOR));
        return db;
    }

    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        db.dialog.setStyle(setDialogColor(DUKE_DIALOG_COLOR));
        return db;
    }
}
