package duke.gui;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.SVGPath;

/**
 * A JavaFX node representing a text box as is usually seen in apps like Telegram and Messenger.
 */
public class TextBox extends VBox {

    /**
     * Constructs a text box with the relevant text placed in a chat bubble.
     * The text box can be constructed to be leftward or rightward facing.
     *
     * @param text text to be placed in the text box.
     * @param left boolean for whether the text box should be left facing (or right otherwise)
     */
    private TextBox(String text, boolean left) {
        //Create text surrounded by a background with rounded corners
        CornerRadii cornerRadii = left
                ? new CornerRadii(0, 10, 10, 10, false)
                : new CornerRadii(10, 0, 10, 10, false);
        BackgroundFill backgroundFill = new BackgroundFill(Color.SKYBLUE,
                cornerRadii,
                new Insets(0));
        Background textBackground = new Background(backgroundFill);
        Label textLabel = new Label(text);
        textLabel.setWrapText(true);
        textLabel.setBackground(textBackground);
        textLabel.setPadding(new Insets(5, 10, 5, 10));

        //Adds a small triangle to the appropriate side to mimic a text box
        HBox hBox = new HBox();
        SVGPath lowerTriangle = new SVGPath();
        lowerTriangle.setFill(Color.SKYBLUE);
        if (left) {
            lowerTriangle.setContent("M0,0 L10,10 L10,0 L0,0");
            hBox.getChildren().addAll(lowerTriangle, textLabel);
        } else {
            lowerTriangle.setContent("M0,0 L-10,10 L-10,0 L0,0");
            hBox.getChildren().addAll(textLabel, lowerTriangle);
        }
        hBox.setSpacing(-1);

        Rectangle verticalGap = new Rectangle(0, 10);
        getChildren().addAll(verticalGap, hBox);

    }

    /**
     * Returns a JavaFX text box node with the contained text and leftward facing.
     *
     * @param text text to be contained within the text box.
     * @return a JavaFX node of a text box with the contained text.
     */
    public static TextBox leftwardTextBox(String text) {
        return new TextBox(text, true);
    }

    /**
     * Returns a JavaFX text box node with the contained text and rightward facing.
     *
     * @param text text to be contained within the text box.
     * @return a JavaFX node of a text box with the contained text.
     */
    public static TextBox rightwardTextBox(String text) {
        return new TextBox(text, false);
    }


}
