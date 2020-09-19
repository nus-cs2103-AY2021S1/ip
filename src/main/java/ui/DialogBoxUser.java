package ui;

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

//Solution below adapted from https://se-education.org/guides/tutorials/javaFxPart4.html

/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBoxUser extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    private DialogBoxUser(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBoxUser.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //this.setBackground(new Background(Color.RED));
        Circle clip = new Circle(50.0, 50.0, 45.0);
        dialog.setText(text);
        displayPicture.setImage(img);
        displayPicture.setClip(clip);
        //this.setBackground(new Background(new BackgroundFill(Color.BLUEVIOLET, CornerRadii.EMPTY, Insets.EMPTY)));
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        setBackground(new Background(new BackgroundFill(Color.YELLOW, CornerRadii.EMPTY, Insets.EMPTY)));
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);

    }

    /**
     * Gets user dialog.
     *
     * @param text String user's Text.
     * @param img Image user's image.
     * @return DialogBox user's dialog box.
     */
    public static DialogBoxUser getUserDialog(String text, Image img) {
        return new DialogBoxUser(text, img);
    }

    /**
     * Gets duke dialog.
     *
     * @param text String duke's text.
     * @param img Image duke's  image.
     * @return DialogBox duke's dialog box
     */
    public static DialogBoxUser getDukeDialog(String text, Image img) {
        DialogBoxUser db = new DialogBoxUser(text, img);
        db.flip();
        return db;
    }
}
