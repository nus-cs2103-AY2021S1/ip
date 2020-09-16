package duke;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.geometry.Insets;
import javafx.scene.shape.Circle;

import java.io.IOException;
import java.util.Collections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import javafx.scene.layout.CornerRadii;

/**
 * Creates user and Duke dialog boxes in GUI.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    /**
     * Constructor for the DialogBox class.
     * 
     * @param text Text to be displayed.
     * @param img Image to be displayed.
     */
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
        dialog.setPadding(new Insets(20,0,0,0));
        displayPicture.setImage(img);
        final Circle circle = new Circle(50, 50, 45);
        displayPicture.setClip(circle);
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
     * Returns user's DialogBox.
     * 
     * @param text Text to be displayed.
     * @param img Image to be displayed.
     * @return User's DialogBox with specified text and image.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        Insets insets = new Insets(0,0,0,0);
        BackgroundFill backgroundFill = new BackgroundFill(Color.web("#BFC8D7"),
                new CornerRadii(30), new Insets(6, 2, 5, 6));
        Background background = new Background(backgroundFill);
        db.setBackground(background);
        return db;
    }

    /**
     * Returns Duke's DialogBox.
     * 
     * @param text Text to be displayed.
     * @param img Image to be displayed.
     * @return Duke's DialogBox with specified text and image.
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        BackgroundFill backgroundFill = new BackgroundFill(Color.web("#E2D2D2"),
                new CornerRadii(30), new Insets(6, 2, 5, 6));
        Background background = new Background(backgroundFill);
        db.setBackground(background);
        return db;
    }
}


