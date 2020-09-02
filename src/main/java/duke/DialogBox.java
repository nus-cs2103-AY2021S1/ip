package duke;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

/**
 * Custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.setPadding(new Insets(10, 0, 10, 0));
        this.setSpacing(10);
        dialog.setText(text);
        displayPicture.setImage(img);
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
     * Constructs the dialog box for Duke.
     *
     * @param text The text that represents the string input to be displayed.
     * @param img The image that represents an icon.
     * @return DialogBox for the user.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        var userDialog = new DialogBox(text, img);
        userDialog.setBackground(new Background(new BackgroundFill(Color.WHITESMOKE,
                CornerRadii.EMPTY,
                Insets.EMPTY)));
        return userDialog;
    }

    /**
     * Constructs the dialog box for Duke.
     *
     * @param text The text that represents the string input to be displayed.
     * @param img The image that represents an icon.
     * @return DialogBox for Duke.
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        db.setBackground(new Background(new BackgroundFill(Color.NAVAJOWHITE,
                CornerRadii.EMPTY,
                Insets.EMPTY)));
        return db;
    }
}
