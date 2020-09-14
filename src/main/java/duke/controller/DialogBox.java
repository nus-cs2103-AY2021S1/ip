package duke.controller;

import java.io.IOException;
import java.util.Collections;

import duke.ui.Messenger;
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
 * Encapsulates a DialogBox class consisting of an ImageView
 * to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private HBox dialogContainer;
    @FXML
    private ImageView displayPicture;


    /**
     * Constructs a DialogBox Instance.
     *
     * @param text the text to display in the dialog box.
     * @param img the image to display in the dialog box.
     */
    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        displayPicture.setImage(img);
        Circle clip = new Circle(50, 50, 45);
        displayPicture.setClip(clip); //convert image to circle
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
     * Sets the alignment of the Dialog container.
     *
     * @param isUserTurn a boolean representing if the dialog box if of the user.
     */
    private void setDialogContainerDirection(boolean isUserTurn) {
        if (isUserTurn) {
            dialogContainer.setAlignment(Pos.CENTER_RIGHT);
        } else {
            dialogContainer.setAlignment(Pos.CENTER_LEFT);
        }
    }

    /**
     * Constructs a DialogBox from the user.
     *
     * @param text the message from the user.
     * @param img the image of the user.
     * @return a DialogBox instance from the user.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        DialogBox userBox = new DialogBox(text, img);
        userBox.setDialogContainerDirection(true);
        return userBox;
    }

    /**
     * Constructs a DialogBox from Duke.
     *
     * @param text the message from Duke.
     * @param img the image of Duke.
     * @return a DialogBox instance from Duke.
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        if (text.length() == 0) {
            text = Messenger.EMPTY_TEXT_FILLER;
        }
        DialogBox dukeBox = new DialogBox(text, img);
        dukeBox.flip();
        dukeBox.setDialogContainerDirection(false);
        return dukeBox;
    }
}
