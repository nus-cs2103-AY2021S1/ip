// Credit: Chan Jun Da

package olivia.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;

/**
 * DialogBox class that represents the messages sent by and to the user.
 */

public class DialogBox extends HBox {

    /** default spacing between dialog and text box */
    private static final double SPACING = 10;

    /**
     * Constructor that creates a DialogBox object, with a text message depending on
     * the input, an image of the sender of the message, and a boolean that determines
     * if the text box should be oriented leftwards or rightwards.
     * @param text a String representing the message to be shown on the text box.
     * @param imageview an ImageView representing a picture of the sender of the message.
     * @param left a boolean representing if the text box should be oriented leftwards.
     */

    private DialogBox(String text, ImageView imageview, boolean left) {
        TextBox output = left
                ? TextBox.leftwardTextBox(text)
                : TextBox.rightwardTextBox(text);

        imageview.setClip(new Circle(50, 50, 50));
        imageview.setFitWidth(100.0);
        imageview.setFitHeight(100.0);
        getChildren().addAll(output, imageview);

        setAlignment(Pos.TOP_RIGHT);
        setSpacing(SPACING);
    }

    /**
     * Flips the alignment of the DialogBox, moving it such that it better reflects a
     * two-way dialogue.
     * @return a flipped DialogBox.
     */

    private DialogBox flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
        return this;
    }

    /**
     * Returns a DialogBox that formats the user's message and picture into a text message.
     * @param message a String representing the user's message.
     * @param iv an ImageView representing the user's picture.
     * @return a DialogBox with the user's message and picture in the format of a text message.
     */

    public static DialogBox getUserDialog(String message, ImageView iv) {
        return new DialogBox(message, iv, false);
    }

    /**
     * Returns a DialogBox that formats Olivia's message and picture into a text message.
     * @param message a String representing the Olivia's message.
     * @param iv an ImageView representing the Olivia's picture.
     * @return a DialogBox with the Olivia's message and picture in the format of a text message.
     */

    public static DialogBox getOliviaDialog(String message, ImageView iv) {
        return new DialogBox(message, iv, true).flip();
    }

}
