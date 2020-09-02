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
 * A custom control using FXML.
 * This control represents a dialog box consisting of a Circle to be filled by images to
 * represent the speaker's face and a label containing text from the speaker.
 */
public class DialogBox extends HBox {
    /** Dialog label for text. */
    @FXML
    private Label dialog;
    /** Circle for speaker's images. */
    @FXML
    private Circle circle;

    /**
     * Constructor for duke.ui.DialogBox.
     *
     * @param text Inputs and responses.
     * @param image Speaker's image.
     */
    private DialogBox(String text, Image image) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        dialog.setText(text);
        circle.setFill(new ImagePattern(image));
    }

    /** Flips the dialog box such that the Circle is on the left and text on the right. */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    /**
     * Creates a new duke.ui.DialogBox for user.
     *
     * @param input User's input.
     * @param image Image of user.
     * @return duke.ui.DialogBox for user.
     */
    public static DialogBox getUserDialog(String input, Image image) {
        return new DialogBox(input, image);
    }

    /**
     * Creates a new duke.ui.DialogBox for duke.Duke.
     *
     * @param response duke.Duke's response.
     * @param image Image of duke.Duke.
     * @return duke.ui.DialogBox for duke.Duke.
     */
    public static DialogBox getDukeDialog(String response, Image image) {
        DialogBox dialogBox = new DialogBox(response, image);
        dialogBox.flip();
        return dialogBox;
    }
}
