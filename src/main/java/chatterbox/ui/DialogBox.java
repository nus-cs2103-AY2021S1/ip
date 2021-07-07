package chatterbox.ui;

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
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

/**
 * DialogBox UI component that encapsulates a label and an imageview.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialogText;
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

        dialogText.setText(text);

        Rectangle clip = new Rectangle(displayPicture.getFitWidth(), displayPicture.getFitHeight());
        clip.setArcWidth(10);
        clip.setArcHeight(10);
        displayPicture.setClip(clip);
        displayPicture.setImage(img);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
        setAlignment(Pos.TOP_RIGHT);
    }

    public static DialogBox getChatterboxDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    public static DialogBox getErrorDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.getStyleClass().add("error-dialog");
        return db;
    }

    public static DialogBox getUserDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.getStyleClass().add("user-dialog");
        db.flip();
        return db;
    }
}
