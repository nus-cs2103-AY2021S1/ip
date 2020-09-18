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
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {
    /** Label of the dialog. **/
    @FXML
    private Label dialog;
    /** Image that will be displayed in the dialog. **/
    @FXML
    private ImageView displayPicture;

    /**
     * Constructs a DialogBox.
     *
     * @param type Type of the DialogBox.
     * @param text Text inside the dialog.
     * @param img Image of the user profile.
     */
    private DialogBox(String type, String text, Image img) {
        String dialogBoxPath = "/view/UserDialogBox.fxml";
        if (type.equals("duke")) {
            dialogBoxPath = "/view/DukeDialogBox.fxml";
        }
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource(dialogBoxPath));
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
     * Gets dialog box for the user.
     *
     * @param text Text inside the dialog.
     * @param img Image of the user profile.
     * @return A DialogBox.
     */
    @FXML
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox("user", text, img);
    }

    /**
     * Gets dialog box for Duke.
     *
     * @param text Text inside the dialog.
     * @param img Image of the user profile.
     * @return A DialogBox.
     */
    @FXML
    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox("duke", text, img);
        db.flip();
        return db;
    }
}
