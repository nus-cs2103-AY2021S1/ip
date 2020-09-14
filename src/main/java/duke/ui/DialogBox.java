package duke.ui;

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
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * FXML Custom control class.
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

        dialog.setText(text);
        displayPicture.setImage(img);
        displayPicture.setClip(new Circle(50, 50, 50));

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
     * User dialog box in the GUI.
     *
     * @param text Text to be displayed in the user dialog box.
     * @param img  User image to be displayed in the user dialog box.
     * @return instance of DialogBox
     */
    public static DialogBox getUserDialog(String text, Image img) {
        //@@author bitterg0d-reused
        //Reused from https://github.com/bitterg0d/duke with minor modifications
        DialogBox userDialog = new DialogBox(text, img);
        userDialog.setAlignment(Pos.BOTTOM_RIGHT);
        userDialog.setMinHeight(Region.USE_PREF_SIZE);
        userDialog.setMaxWidth(370.0);
        userDialog.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, new CornerRadii(10.0), new Insets(5, 0, 5, 3))));
        //@@author
        return userDialog;
    }

    /**
     * Duke dialog box in the GUI.
     *
     * @param text Text to be displayed in the duke dialog box.
     * @param img  Duke image to be displayed in the duke dialog box.
     * @return instance of DialogBox
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        //@@author bitterg0d-reused
        //Reused from https://github.com/bitterg0d/duke with minor modifications
        DialogBox dukeDialog = new DialogBox(text, img);
        dukeDialog.flip();
        dukeDialog.setAlignment(Pos.BOTTOM_LEFT);
        dukeDialog.setMinHeight(Region.USE_PREF_SIZE);
        dukeDialog.setMaxWidth(370.0);
        dukeDialog.setBackground(new Background(new BackgroundFill(Color.LIGHTGREEN, new CornerRadii(10.0), new Insets(5, 0, 5, 3))));
        //@@author
        return dukeDialog;
    }
}
