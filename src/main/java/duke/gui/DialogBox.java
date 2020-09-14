package duke.gui;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

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
    }

    private void setResponseStyle() {
        dialog.setStyle("-fx-label-padding: 10; -fx-background-radius: 10; -fx-background-color: #66fcf1; "
                + "-fx-max-width: 325;");
    }

    private void setExceptionStyle() {
        dialog.setStyle("-fx-label-padding: 10; -fx-background-radius: 10; -fx-background-color: #fc493d; "
                + "-fx-max-width: 325;");
    }

    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.setResponseStyle();
        return db;
    }

    public static DialogBox getExceptionDialog(String text, Image img) {
        assert text.contains("Ex: ") && text.length() > 4;
        String actualText = text.substring(text.indexOf(' ') + 1);
        var db = new DialogBox(actualText, img);
        db.setExceptionStyle();
        return db;
    }
}
