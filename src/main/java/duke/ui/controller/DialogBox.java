package duke.ui.controller;

import java.io.IOException;
import java.util.Collections;

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
import javafx.scene.layout.VBox;

/**
 * Represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;
    @FXML
    private VBox chatBox;
    @FXML
    private HBox chatRow;

    /**
     * Constructs a <code>DialogBox</code>.
     * @param text a string of input places in the box.
     * @param img an image representing the speaking person.
     */
    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException exception) {
            exception.printStackTrace();
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
        chatBox.setAlignment(Pos.TOP_LEFT);
        setAlignment(Pos.CENTER_LEFT);
    }

    /**
     * Generates the user dialog.
     *
     * @param text the input from the user.
     * @param img the image of the user.
     * @return a <code>DialogBox</code> of the user.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    /**
     * Generates the Duke dialog.
     *
     * @param text the input from Duke.
     * @param img the image of Duke.
     * @return a <code>DialogBox</code> of Duke.
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        var dialogBox = new DialogBox(text, img);
        dialogBox.flip();
        dialogBox.chatBox.setStyle("-fx-background-color: lightblue; -fx-background-radius: 13");
        HBox.setMargin(dialogBox.chatBox, new Insets(0, 15, 0, 5));
        return dialogBox;
    }
}
