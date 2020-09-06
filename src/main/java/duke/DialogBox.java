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
 * Represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    /**
     * Constructs a dialogbox and load the fxml file.
     *
     * @param input Input text.
     * @param image Image of the user or Duke.
     */
    public DialogBox(String input, Image image) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
            dialog.setText(input);
            Circle clip = new Circle(displayPicture.getFitWidth() / 2,
                    displayPicture.getFitHeight() / 2,
                    displayPicture.getFitWidth() / 2);
            displayPicture.setClip(clip);
            displayPicture.setImage(image);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and
     * the text is on the right.
     */
    public void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        this.getChildren().setAll(tmp);
        this.setAlignment(Pos.TOP_LEFT);
    }

    /**
     * Gets the user dialog box.
     *
     * @param input User input.
     * @param userImage User image.
     * @return The user dialog box.
     */
    public static DialogBox getUserDialog(String input, Image userImage) {
        return new DialogBox(input, userImage);
    }

    /**
     * Gets the duke dialog box.
     *
     * @param response Duke's response.
     * @param dukeImage Duke's image.
     * @return The duke dialog box.
     */
    public static DialogBox getDukeDialog(String response, Image dukeImage) {
        var db = new DialogBox(response, dukeImage);
        db.flip();
        return db;
    }


}
