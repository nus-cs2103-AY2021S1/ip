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
import javafx.scene.layout.VBox;

/**
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {

    private static final String PATH_TO_FXML = "/view/DialogBox.fxml";
    private static final String PATH_TO_CSS = "/view/DialogBox.css";

    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;
    @FXML
    private VBox container;

    /**
     * Opens the dialog box in the GUI.
     *
     * @param text Text to output.
     * @param image Image of DialogBox (either Duke or User).
     */
    private DialogBox(String text, Image image) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource(PATH_TO_FXML));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        createDialogBox(text, image);
    }

    /**
     * Creates the dialog box.
     *
     * @param text Text to display.
     * @param image Image to display.
     */
    private void createDialogBox(String text, Image image) {
        container.getStylesheets().add(PATH_TO_CSS);
        dialog.getStylesheets().add(PATH_TO_CSS);
        dialog.setText(text);
        displayPicture.setImage(image);
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
     * Displays the user's dialog.
     *
     * @param text Input text from user.
     * @param img User's icon.
     * @return Display of user dialog.
     */
    protected static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    /**
     * Displays Duke's response.
     *
     * @param text Output text from Duke.
     * @param img Duke image.
     * @return Display of Duke's response.
     */
    protected static DialogBox getDukeDialog(String text, Image img) {
        var dialogBox = new DialogBox(text, img);
        dialogBox.flip();
        return dialogBox;
    }
}
