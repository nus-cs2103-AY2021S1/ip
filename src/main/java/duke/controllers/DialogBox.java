package duke.controllers;

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
import javafx.scene.shape.Rectangle;

/**
 * Controller for DialogBox. This control represents a dialog box consisting of an ImageView to represent the speaker's
 * face and a label containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private HBox speechBubble;
    @FXML
    private ImageView displayPicture;

    /**
     * Constructs a {@code DialogBox} object with the specified text and image.
     *
     * @param text the text to be printed
     * @param image the image to be displayed
     */
    private DialogBox(String text, Image image) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/views/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        displayPicture.setImage(image);
        displayPicture.setClip(generateClip(displayPicture, 50));
    }

    /**
     * Generates a clip for the {@code ImageView} with the specified corner radius.
     *
     * @param imageView the {@code ImageView} to inherit the width and height from.
     * @param cornerRadius the corner radius value.
     * @return a clip for the {@code ImageView}.
     */
    private static Rectangle generateClip(ImageView imageView, int cornerRadius) {
        Rectangle clip = new Rectangle();
        clip.setWidth(imageView.getFitWidth());
        clip.setHeight(imageView.getFitHeight());
        clip.setArcWidth(cornerRadius);
        clip.setArcHeight(cornerRadius);
        return clip;
    }

    /**
     * Flips the dialog box such that the {@code ImageView} is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tempList = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tempList);
        getChildren().setAll(tempList);
        setAlignment(Pos.TOP_LEFT);
    }

    /**
     * Returns a {@code DialogBox} with the {@code ImageView} on the right and text on the left.
     *
     * @param text the text to be printed
     * @param image the image to be displayed
     * @return a {@code DialogBox} for the user
     */
    public static DialogBox getUserDialog(String text, Image image) {
        DialogBox dialogBox = new DialogBox(text, image);
        dialogBox.speechBubble.getStyleClass().add("user-speech-bubble");
        return dialogBox;
    }

    /**
     * Returns a {@code DialogBox} with the {@code ImageView} on the left and text on the right.
     *
     * @param text the text to be printed
     * @param image the image to be displayed
     * @return a {@code DialogBox} for the chatbot
     */
    public static DialogBox getDukeDialog(String text, Image image) {
        DialogBox dialogBox = new DialogBox(text, image);
        dialogBox.flip();
        return dialogBox;
    }
}
