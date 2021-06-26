package duke.ui;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

/**
 * A custom JavaFX component for displaying Duke's or the user's message together with a display picture.
 */
public class DialogBox extends HBox {

    @FXML
    private Label dialog;

    @FXML
    private ImageView displayPicture;

    private DialogBox(String text, Image image) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        displayPicture.setImage(image);
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

    private void formatAsDukeError() {
        dialog.setBackground(new Background(new BackgroundFill(Color.ORANGE, null, null)));
        dialog.setTextFill(Color.WHITE);
    }

    private void formatAsDuke() {
        dialog.setBackground(new Background(new BackgroundFill(Color.gray(0.9), null, null)));
    }

    private void formatAsUser() {
        dialog.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, null, null)));
    }

    /**
     * Creates a DialogBox to display the user's inputs, with the display picture on the right and right-aligned text.
     *
     * @param text User's input text to display.
     * @param image User's display picture.
     * @return DialogBox to display the user's input.
     */
    public static DialogBox getUserDialog(String text, Image image) {
        DialogBox db = new DialogBox(text, image);
        db.formatAsUser();
        return db;
    }

    /**
     * Creates a DialogBox to display Duke's response, with the display picture on the left and left-aligned text.
     *
     * @param text Duke's response to display.
     * @param image Duke's display picture.
     * @return DialogBox to display Duke's responses.
     */
    public static DialogBox getDukeDialog(String text, Image image, boolean isError) {
        var db = new DialogBox(text, image);
        db.flip();
        if (isError) {
            db.formatAsDukeError();
        } else {
            db.formatAsDuke();
        }
        return db;
    }
}
