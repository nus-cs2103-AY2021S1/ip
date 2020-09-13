package duke.ui;

import java.io.IOException;
import java.util.Collections;

import duke.main.Duke;
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
 * Class representing a GUI box that contains a textbox and an image avatar.
 * DialogBoxes sent by the user have their avatar on the right, while those
 * representing Duke's response have its avatar on the left instead.
 */
public class DialogBox extends HBox {
    // @@author Jeffry Lum-reused
    // Reused from https://se-education.org/guides/tutorials/javaFxPart4.html with minor modifications
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

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
        dialog.setPadding(new Insets(10));
        displayPicture.setImage(img);
    }

    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.CENTER_LEFT);
    }

    /**
     * Creates a DialogBox item representing a command sent by a user.
     * @param text User input.
     * @param img User avatar.
     * @return DialogBox object containing the user's input and avatar.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    /**
     * Creates a DialogBox item representing a response from Duke.
     * @param text Duke's response.
     * @param img Duke's avatar.
     * @return DialogBox object containing the Duke's avatar and response.
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        DialogBox db = new DialogBox(text, img);
        db.flip();
        return db;
    }
    // @@author

    public static DialogBox getDukeGreeting(Image img) {
        DialogBox db = new DialogBox(Duke.GREETING, img);
        db.flip();
        return db;
    }
}
