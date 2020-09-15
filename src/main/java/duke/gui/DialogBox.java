package duke.gui;

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
import javafx.scene.shape.Circle;

/**
 * Class that defines the dialog box during interactions between user and Duke.
 */
public class DialogBox extends HBox {
    @FXML
    private Label text;
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
        final Circle clip = new Circle(45, 45, 45);
        displayPicture.setClip(clip);
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
        // @@author Rishi5154-reused
        // Background colour and curve code idea reused from @@jiayushe
        var db = new DialogBox(text, img);
        BackgroundFill fill = new BackgroundFill(Color.PALEGOLDENROD, new CornerRadii(15),
                new Insets(5, 5, 5, 5)
        );
        Background background = new Background(fill);
        db.setBackground(background);
        return db;
        //@@author
    }

    public static DialogBox getDukeDialog(String text, Image img) {
        // @@author Rishi5154-reused
        // Background colour and curve code idea reused from @@jiayushe
        var db = new DialogBox(text, img);
        db.flip();
        BackgroundFill fill = new BackgroundFill(Color.SKYBLUE, new CornerRadii(15),
                new Insets(5, 5, 5, 5)
        );
        Background background = new Background(fill);
        db.setBackground(background);
        return db;
        //@@author
    }
}
