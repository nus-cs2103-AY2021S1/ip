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
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
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

        // dialog.setStyle("-fx-font-family: monospace");
        Circle circle = new Circle( 32, 35, 32);
        displayPicture.setClip(circle);
        dialog.setText(text);
        displayPicture.setImage(img);
        dialog.setMinSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
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
        DialogBox dialogBox = new DialogBox(text, img);
        Color backgroundColor = Color.SALMON;
        CornerRadii radii = new CornerRadii(20.00);
        Insets insets = new Insets(5);
        BackgroundFill backgroundFill = new BackgroundFill(backgroundColor, radii, insets);
        Background background = new Background(backgroundFill);
        dialogBox.setBackground(background);
        return dialogBox;
    }

    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        Color backgroundColor = Color.LIGHTSTEELBLUE;
        CornerRadii radii = new CornerRadii(30.00);
        Insets insets = new Insets(10);
        BackgroundFill backgroundFill = new BackgroundFill(backgroundColor, radii, insets);
        Background background = new Background(backgroundFill);
        db.setBackground(background);
        db.flip();
        return db;
    }
}
