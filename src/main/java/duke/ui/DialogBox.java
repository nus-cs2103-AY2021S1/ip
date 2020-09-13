package duke.ui;

import java.io.IOException;
import java.util.Collections;

import duke.MainWindow;
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
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;
    @FXML
    private Label userDialog;

    private DialogBox(String text, Image img, String type) {
        try {
            FXMLLoader fxmlLoader;
            if (type.equals("user")) {
                fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/userDialogBox.fxml"));
            } else {
                fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            }
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (type.equals("user")) {
            userDialog.setText(text);
        } else {
            dialog.setText(text);
        }
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
     * Gets dialog box to display messages by the user.
     *
     * @param text Message by the user.
     * @param img Display image of the user.
     * @return A new instance of DialogBox.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        String type = "user";
        return new DialogBox(text, img, type);
    }

    /**
     * Gets dialog box to display messages by the Mrs Dino.
     *
     * @param text Message by Mrs Dino.
     * @param img Display image of Mrs Dino.
     * @return A new instance of DialogBox.
     */
    public static DialogBox getMrsDinoDialog(String text, Image img) {
        String type = "duke";
        var db = new DialogBox(text, img, type);
        db.flip();
        return db;
    }
}
