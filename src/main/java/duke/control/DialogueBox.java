package duke.control;

import java.io.IOException;

import duke.controller.MainWindow;
import duke.core.MessageType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class DialogueBox extends HBox {
    @FXML
    private Text label;

    @FXML
    private ImageView picture;

    private DialogueBox(String text, Image img, MessageType messageType) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogueBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        label.setText(text);
        label.setFill(getMessageColor(messageType));
        label.setFont(getMessageFont(messageType));

        Circle circle = new Circle(45, 45, 45);
        picture.setImage(img);
        picture.setClip(circle);
    }

    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    public static DialogueBox getUserDialogueBox(String string, Image image, MessageType messageType) {
        DialogueBox dialogueBox = new DialogueBox(string, image, messageType);

        dialogueBox.label.setWrappingWidth(224);
        dialogueBox.setBackground(
                new Background(
                    new BackgroundFill(Color.GREEN,
                            new CornerRadii(10),
                            new Insets(3, 0, 3, 50))));

        HBox.setMargin(dialogueBox.label, new Insets(10, 0, 0, 50));

        return dialogueBox;
    }

    public static DialogueBox getDukeDialogueBox(String string, Image image, MessageType messageType) {
        DialogueBox dialogueBox = new DialogueBox(string, image, messageType);

        dialogueBox.flip();

        dialogueBox.label.setWrappingWidth(200);
        dialogueBox.setBackground(
                new Background(
                        new BackgroundFill(Color.YELLOW,
                                new CornerRadii(10),
                                new Insets(3, 50, 3, 5))));


        HBox.setMargin(dialogueBox.label, new Insets(0, 50, 0, 0));

        return dialogueBox;
    }

    /**
     * Returns the color of the message of the corresponding message type.
     *
     * @param messageType The type of the message.
     * @return The color of the message of the corresponding message type.
     */
    Paint getMessageColor(MessageType messageType) {
        if(messageType == MessageType.HANDLE_MESSAGE) {
            return Color.BROWN;
        } else if(messageType == MessageType.COMMAND_NOT_FOUND_MESSAGE) {
            return Color.RED;
        } else if(messageType == MessageType.TASK_NOT_FOUND_MESSAGE) {
            return Color.BLUE;
        } else if (messageType == MessageType.USER_MESSAGE) {
            return Color.WHITE;
        } else {
            return Color.BLACK;
        }
    }

    /**
     * Returns the font of the message of the corresponding message type.
     *
     * @param messageType The type of the message.
     * @return The font of the message of the corresponding message type.
     */
    Font getMessageFont(MessageType messageType) {
        if(messageType == MessageType.HANDLE_MESSAGE) {
            return Font.font("Verdana", FontWeight.BOLD, 10);
        } else if (messageType == MessageType.TASK_NOT_FOUND_MESSAGE) {
            return Font.font("Verdana", FontPosture.ITALIC, 10);
        } else if (messageType == MessageType.COMMAND_NOT_FOUND_MESSAGE) {
            return Font.font("Verdana", FontWeight.BOLD, 10);
        } else {
            return Font.font("Arial", 10);
        }
    }
}
