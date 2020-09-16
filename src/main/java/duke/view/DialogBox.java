package duke.view;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class DialogBox extends HBox {
    private static final Image userImage = new Image(DialogBox.class.getResourceAsStream("/images/user-solid.png"));
    private static final Image dukeImage = new Image(DialogBox.class.getResourceAsStream("/images/crown-solid.png"));

    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/views/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.dialog.setText(text);
        this.displayPicture.setImage(img);
    }

    public static DialogBox getUserDialog(String text) {
        DialogBox dialogBox = new DialogBox(text, DialogBox.userImage);
        dialogBox.getStyleClass().add("user-dialog-box");

        return dialogBox;
    }

    public static DialogBox getDukeDialog(String text) {
        return new DialogBox(text, DialogBox.dukeImage);
    }

    public static DialogBox getErrorDialog(String text) {
        DialogBox dialogBox = new DialogBox(text, DialogBox.dukeImage);
        dialogBox.getStyleClass().add("error-dialog-box");

        return dialogBox;
    };
}
