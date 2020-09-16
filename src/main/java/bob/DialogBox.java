package bob;

import java.io.IOException;
import java.util.Collections;

import bob.MainWindow;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
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
    private Circle displayPicture;


    private DialogBox(String text, Image img, String path) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource(path));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        displayPicture.setStroke(Color.DODGERBLUE);
        displayPicture.setFill(new ImagePattern(img));
        displayPicture.setEffect(new DropShadow(+25d, 0d, +2d, Color.DODGERBLUE));
        dialog.setText(text);
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

    public static DialogBox getUserDialog(String text, Image img) {
        var db = new DialogBox(text, img, "/view/DialogBoxUser.fxml");
        return db;
    }

    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img, "/view/DialogBox.fxml");
        db.flip();
        return db;
    }

    public static DialogBox getDukeDialogError(String text, Image img) {
        var db = new DialogBox(text, img, "/view/DialogBoxError.fxml");
        db.flip();
        db.displayPicture.setStroke(Color.CRIMSON);
        db.displayPicture.setEffect(new DropShadow(+25d, 0d, +2d, Color.CRIMSON));
        return db;
    }

}