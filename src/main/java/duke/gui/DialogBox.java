package duke.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.io.IOException;
import java.util.Collections;

/**
 * Represents a dialog box consisting of an ImageView to represent the speaker's face
 * and a label containing text from the speaker.
 */
public class DialogBox extends HBox {

    @FXML
    private Label dialog;
    @FXML
    private Circle displayPicture;

    /**
     * Creates a dialog box with a text and an image.
     *
     * @param text Text.
     * @param img Image.
     */
    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.dialog.setText(text);
        this.displayPicture.setFill(new ImagePattern(img));
    }

    /**
     * Gets a user dialog box.
     *
     * @param text Text.
     * @param img Image.
     * @return Dialog box with the user's image and text.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        DialogBox db = new DialogBox(text, img);
        BackgroundFill backgroundFill = new BackgroundFill(
                Color.LIGHTGRAY,
                new CornerRadii(5),
                Insets.EMPTY);
        db.dialog.setBackground(new Background(backgroundFill));
        return db;
    }

    /**
     * Gets a Duke dialog box.
     *
     * @param text Text.
     * @param img Image.
     * @return Dialog box with Duke's image and text.
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        DialogBox db = new DialogBox(text, img);
        db.flip();
        boolean isError = db.isErrorMessage(text);
        if (isError) {
            db.dialog.setTextFill(Color.RED);
            BorderStroke borderStroke = new BorderStroke(
                    Color.RED,
                    BorderStrokeStyle.SOLID,
                    new CornerRadii(5),
                    BorderWidths.DEFAULT);
            db.dialog.setBorder(new Border(borderStroke));
        } else {
            BackgroundFill backgroundFill = new BackgroundFill(
                    Color.LIGHTGREEN,
                    new CornerRadii(5),
                    Insets.EMPTY);
            db.dialog.setBackground(new Background(backgroundFill));
        }
        return db;
    }

    /**
     * Checks if the response is an error message.
     *
     * @param response String describing the response.
     * @return If the response is an error message.
     */
    private boolean isErrorMessage(String response) {
        String[] splitResponse = response.split(" ", 2);
        String firstWord = splitResponse[0];
        return firstWord.contains("ERROR:");
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
