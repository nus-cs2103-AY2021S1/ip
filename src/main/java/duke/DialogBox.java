package duke;

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
import javafx.scene.shape.Circle;

/**
 * An example of a custom control using FXML.
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
        displayPicture.setClip(new Circle(25, 25, 50));
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.CENTER_LEFT);
    }

    public static DialogBox getUserDialog(String text, Image img) {
        var userDialog = new DialogBox(text, img);
        String userDialogStyle = "-fx-background-color: rgba(199,84,84,0.93); -fx-padding: 11 11 11 11; "
                + "-fx-background-radius: 5; -fx-text-fill: #EDEDED;";
        userDialog.dialog.setStyle(userDialogStyle);
        userDialog.dialog.setTranslateX(-5);
        return userDialog;
    }

    public static DialogBox getDukeDialog(String text, Image img) {
        var dukeDialog = new DialogBox(text, img);
        String dukeDialogStyle = "-fx-background-color: rgba(87,178,245,0.96); -fx-padding: 11 11 11 11; "
                + "-fx-background-radius: 5; -fx-text-fill: #006464;";
        dukeDialog.dialog.setStyle(dukeDialogStyle);
        dukeDialog.dialog.setTranslateX(5);
        dukeDialog.flip();
        return dukeDialog;
    }
}
