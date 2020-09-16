package rogue.ui;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face
 * and a label containing text from the speaker.
 */
public class DialogBox extends HBox {
    private static enum Style {
        USER, ROGUE
    };

    /** The radius of the circular clip for the image. */
    private static final double RADIUS_CIRCLE_CLIP = 25.0;

    @FXML
    private HBox dialogContainer;
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    private DialogBox(String text, Image img, Style style) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        setText(text);
        setImage(img);
        setStyle(style);
    }

    /**
     * Creates a {@code DialogBox} for the user.
     *
     * @param text  The text to display.
     * @param img   The image to display.
     * @return A {@code DialogBox} with image displayed on the right.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img, Style.USER);
    }

    /**
     * Creates a {@code DialogBox} for {@code Rogue}.
     *
     * @param text  The text to display.
     * @param img   The image to display.
     * @return A {@code DialogBox} with image displayed on the left.
     */
    public static DialogBox getRogueDialog(String text, Image img) {
        var db = new DialogBox(text, img, Style.ROGUE);
        db.flip();
        return db;
    }

    /**
     * Sets the text of the {@code DialogBox}.
     *
     * @param text The text to set.
     */
    private void setText(String text) {
        dialog.setText(text);
    }

    /**
     * Sets the image of the {@code DialogBox}.
     *
     * @param img The image to set.
     */
    private void setImage(Image img) {
        displayPicture.setImage(img);
        clipPictureToCircle();
    }

    /**
     * Sets the style of the {@code DialogBox}.
     *
     * USER: sets top right background radius to 0.
     * ROGUE: sets top left background radius to 0 and background color to #30D158.
     *
     * @param style The style to set.
     */
    private void setStyle(Style style) {
        switch (style) {
        case USER:
            dialogContainer.setStyle("-fx-background-radius: 1.5em 0 1.5em 1.5em;");
            break;

        case ROGUE:
            dialogContainer.setStyle("-fx-background-radius: 0 1.5em 1.5em 1.5em;"
                    + "-fx-background-color: #30D158;");
            break;

        default:
            // Should not reach here
            break;
        }
    }

    /**
     * Clips the display picture into a circle.
     */
    private void clipPictureToCircle() {
        // Clips image to a circle.
        Circle clip = new Circle(RADIUS_CIRCLE_CLIP, RADIUS_CIRCLE_CLIP, RADIUS_CIRCLE_CLIP);
        displayPicture.setClip(clip);
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
}
