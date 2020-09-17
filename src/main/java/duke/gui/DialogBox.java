package duke.gui;

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
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * <code>Node</code> that contains an avatar image and output text that represents conversation between Duke and the
 * user.
 */
public class DialogBox extends HBox {

    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    /**
     * Constructor for a <code>DialogBox</code>. Customizes the text, image and font of the displayed contents.
     *
     * @param text Text for display in <code>dialog</code>.
     * @param img Image for display in <code>displayPicture</code>.
     * @param font Font to be used for text.
     * @param color Color to be used for text.
     */
    private DialogBox(String text, Image img, Font font, Color color) {

        // Load component from FXML
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        dialog.setFont(font);
        dialog.setTextFill(color);
        displayPicture.setImage(img);

    }

    /**
     * Constructor that defaults text color to black.
     *
     * @param text Text for display in <code>dialog</code>.
     * @param img Image for display in <code>displayPicture</code>.
     * @param font Font to be used for text.
     */
    private DialogBox(String text, Image img, Font font) {
        this(text, img, font, Color.BLACK);
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
     * Creates a <code>DialogBox</code> for a message by the user.
     *
     * @param text User input text.
     * @param img User avatar image.
     * @param font Font for user text.
     * @return Initialized <code>DialogBox</code> for user message for display in GUI.
     */
    public static DialogBox getUserDialog(String text, Image img, Font font) {
        return new DialogBox(text, img, font);
    }

    /**
     * Creates a <code>DialogBox</code> for a message by Duke.
     *
     * @param text Duke output text.
     * @param img Duke avatar image.
     * @param font Font for Duke's text.
     * @return Initialized <code>DialogBox</code> for Duke's messages for display in GUI.
     */
    public static DialogBox getDukeDialog(String text, Image img, Font font) {
        DialogBox db = new DialogBox(text, img, font);

        // Flips contents so that avatar image for Duke is on the left.
        db.flip();

        return db;
    }

    /**
     * Creates a <code>DialogBox</code> for a warning by Duke.
     *
     * @param text Duke output text.
     * @param img Duke avatar image.
     * @param font Font for Duke's text.
     * @return Initialized <code>DialogBox</code> for Duke's warning for display in GUI.
     */
    public static DialogBox getDukeWarningDialog(String text, Image img, Font font) {
        DialogBox db = new DialogBox(text, img, font, Color.RED);

        db.flip();

        return db;
    }


}
