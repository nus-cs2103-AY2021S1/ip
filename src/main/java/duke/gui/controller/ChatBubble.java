package duke.gui.controller;

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
 * Helper class to generate a chat bubble
 */
public class ChatBubble extends HBox {
    @FXML
    private Label messageLabel;
    @FXML
    private ImageView avatarView;

    /**
     * Generate a chat bubble
     * @param message message of the chat bubble
     * @param avatar profile picture of the chat user
     */
    private ChatBubble(String message, Image avatar) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/ChatBubble.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        messageLabel.setText(message);
        avatarView.setImage(avatar);

        // @@author akgrenSoar-reused
        // Source: https://stackoverflow.com/a/20490028/6943913
        Rectangle clip = new Rectangle(
                avatarView.boundsInParentProperty().get().getWidth(),
                avatarView.boundsInParentProperty().get().getHeight());
        clip.setArcWidth(20);
        clip.setArcHeight(20);

        // Clip the avatarView
        avatarView.setClip(clip);
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
     * Generate a user chat bubble
     * @param message message to display in chat bubble
     * @param avatar user's profile picture to display in chat bubble
     * @return Chat bubble containing user's message and avatar
     */
    public static ChatBubble getUserDialog(String message, Image avatar) {
        return new ChatBubble(message, avatar);
    }

    /**
     * Generate a duke chat bubble
     * @param message message to display in chat bubble
     * @param avatar duke's profile picture to display in chat bubble
     * @return Chat bubble containing duke's message and avatar
     */
    public static ChatBubble getDukeDialog(String message, Image avatar) {
        ChatBubble chatBubble = new ChatBubble(message, avatar);
        chatBubble.flip();
        return chatBubble;
    }

    /**
     * Generate a error message chat bubble
     * @param message message to display in chat bubble
     * @param avatar error message's profile picture to display in chat bubble
     * @return Chat bubble containing error message's message and avatar
     */
    public static ChatBubble getErrorDialog(String message, Image avatar) {
        ChatBubble chatBubble = new ChatBubble(message, avatar);
        chatBubble.flip();
        chatBubble.setStyle("-fx-background-color: #ffcccc");
        return chatBubble;
    }

}
