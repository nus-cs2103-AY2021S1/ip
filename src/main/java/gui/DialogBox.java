package gui;

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
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;


/**
 * An FXML control representing a dialog box consisting of
 * an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    private final Circle circle = new Circle();

    public enum Direction {
        USER, DUKE
    }

    private DialogBox(String text, Image img, Direction dir) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(
                    MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        circle.setCenterX(38);
        circle.setCenterY(38);
        circle.setRadius(38);
        displayPicture.setClip(circle);
        displayPicture.setImage(img);

        dialog.setText(text);
        dialog.setWrapText(true);
        dialog.setPadding(new Insets(10, 15, 10, 15));
        if (dir == Direction.USER) {
            dialog.setStyle("-fx-background-color: #64FF64; " +
                    "-fx-background-radius: 45;");
        } else {
            dialog.setStyle("-fx-background-color: #FFFFFF; " +
                    "-fx-background-radius: 45;");
            this.flip();
        }

    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp =
                FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img, Direction.USER);
    }

    public static DialogBox getDukeDialog(String text, Image img) {
        return new DialogBox(text, img, Direction.DUKE);
    }
}