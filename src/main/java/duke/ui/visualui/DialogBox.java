package duke.ui.visualui;

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
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

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
    @FXML
    private VBox speechBubble;

    private DialogBox(Stage stage, String text, Image img, String user) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        dialog.setText(text);
        dialog.getStylesheets().add("view/DialogBox.css");
        if (user.equals("user")) {
            speechBubble.setStyle("-fx-background-radius: 12 12 0 12;");
        } else {
            speechBubble.setStyle("-fx-background-radius: 12 12 12 0;");
        }
        speechBubble.getStylesheets().add("view/DialogBox.css");
        Rectangle clip = new Rectangle(displayPicture.getFitWidth(), displayPicture.getFitHeight());
        clip.setArcWidth(80);
        clip.setArcHeight(80);
        displayPicture.setClip(clip);
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

    public static DialogBox getUserDialog(Stage stage, String text, Image img) {
        return new DialogBox(stage, text, img, "user");
    }

    public static DialogBox getDukeDialog(Stage stage, String text, Image img) {
        var db = new DialogBox(stage, text, img, "duke");
        db.flip();
        return db;
    }
}
