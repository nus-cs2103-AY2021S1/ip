package olivia.ui;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.SVGPath;

/**
 * A JavaFX node representing a text box as is usually seen in apps like Telegram and Messenger.
 */
public class TextBox extends VBox {

    /** default radius of round corners in text box */
    private static final double CORNER_RADIUS = 10;
    /** default top/bottom padding */
    private static final double LABEL_PADDING_VERTICAL = 5;
    /** default left-right padding */
    private static final double LABEL_PADDING_HORIZONTAL = 10;
    /** SVG path for left triangle */
    private static final String PATH_LEFT_TRIANGLE = "M0,0 L10,10 L10,0 L0,0";
    /** SVG path for right triangle */
    private static final String PATH_RIGHT_TRIANGLE = "M0,0 L-10,10 L-10,0 L0,0";
    /** vertical gap between text box peak and circle image peak */
    private static final double BUFFER_TOP = 10;
    /** leftward box colour */
    private static final Color LEFTWARD_COLOR = Color.GAINSBORO;
    /** rightward box colour */
    private static final Color RIGHTWARD_COLOR = Color.DARKSEAGREEN;

    /**
     * Constructs a text box with the relevant text placed in a chat bubble.
     * The text box can be constructed to be leftward or rightward facing.
     *
     * @param text text to be placed in the text box.
     * @param left boolean for whether the text box should be left facing (or right otherwise)
     */
    private TextBox(String text, boolean left) {
        Label textLabel = new Label(text);
        textLabel.setWrapText(true);
        textLabel.setBackground(getDefaultBackground(left));
        textLabel.setPadding(getDefaultPadding());

        //Adds a small triangle to the appropriate side to mimic a text box
        HBox hBox = new HBox();
        SVGPath lowerTriangle = new SVGPath();
        if (left) {
            lowerTriangle.setContent(PATH_LEFT_TRIANGLE);
            lowerTriangle.setFill(LEFTWARD_COLOR);
            hBox.getChildren().addAll(lowerTriangle, textLabel);
        } else {
            lowerTriangle.setContent(PATH_RIGHT_TRIANGLE);
            lowerTriangle.setFill(RIGHTWARD_COLOR);
            hBox.getChildren().addAll(textLabel, lowerTriangle);
        }
        hBox.setSpacing(-1); //forces both shapes to overlap with each other

        Rectangle verticalGap = new Rectangle(0, BUFFER_TOP);
        getChildren().addAll(verticalGap, hBox);

    }

    /** Returns a background to fill up the text box with a colour and shape */
    private Background getDefaultBackground(boolean left) {
        CornerRadii cornerRadii = left
                ? new CornerRadii(0, CORNER_RADIUS,
                CORNER_RADIUS, CORNER_RADIUS, false)
                : new CornerRadii(CORNER_RADIUS, 0,
                CORNER_RADIUS, CORNER_RADIUS, false);
        BackgroundFill backgroundFill = new BackgroundFill(
                left ? LEFTWARD_COLOR : RIGHTWARD_COLOR,
                cornerRadii,
                new Insets(0));
        return new Background(backgroundFill);
    }

    /** Returns an Insets object which represents the padding of the text in the label */
    private Insets getDefaultPadding() {
        return new Insets(LABEL_PADDING_VERTICAL, LABEL_PADDING_HORIZONTAL,
                LABEL_PADDING_VERTICAL, LABEL_PADDING_HORIZONTAL);
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