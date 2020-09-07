package duke.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;

/**
 * JavaFX node of a dialog box consisting of a picture and a text box.
 */
public class DialogBox extends HBox {

    /** default radius of user's image in the dialog box */
    private static final double IMAGE_RADIUS = 35.0;

    /** text to be contained in the text box */
    private TextBox text;
    /** image to be used in the dialog box */
    private Circle displayPicture;

    /** constructs a dialog box from the given text, image and direction */
    private DialogBox(String text, Image image, boolean left) {
        this.text = left
                ? TextBox.leftwardTextBox(text)
                : TextBox.rightwardTextBox(text);
        displayPicture = CircleImage.createCircleImage(IMAGE_RADIUS, image);

        setAlignment(Pos.TOP_RIGHT);
        getChildren().addAll(this.text, displayPicture);

        setSpacing(10.0);
    }
    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    /**
     * Returns a JavaFX dialog box with the user's message and a given image.
     *
     * @param userMessage message sent by the user.
     * @param image image of the user.
     * @return a javaFX node containing the user's image and the user's text.
     */
    public static DialogBox getUserDialog(String userMessage, Image image) {
        return new DialogBox(userMessage, image, false);
    }

    /**
     * Returns a JavaFX dialog box with duke's message and a given image.
     *
     * @param dukeMessage message sent by duke.
     * @param image image of duke.
     * @return a javaFX node containing the duke's image and the duke's text.
     */
    public static DialogBox getDukeDialog(String dukeMessage, Image image) {
        DialogBox box = new DialogBox(dukeMessage, image, true);
        box.flip();
        return box;
    }


}
