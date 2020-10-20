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
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

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

    /**
     * Initializes a DialogBox based on given text and avatar for Duke output.
     *
     * @param text Text to be printed.
     * @param img Avatar to be displayed.
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
        Text response = new Text(text);
        response.setFont(new Font(13));
        dialog.setLineSpacing(1.15);
        dialog.setTranslateX(-5);
        dialog.setTextAlignment(TextAlignment.RIGHT);

        dialog.setText(text);
        displayPicture.setImage(img);
        displayPicture.setClip(new Circle(50, 50, 49));
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
     * Generates a DialogBox based on given text and avatar for user input.
     *
     * @param text Text to be printed.
     * @param img Avatar to be displayed.
     * @return JavaFX DialogBox containing the given text and avatar.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        BackgroundFill back = new BackgroundFill(
                Color.web("#cfe8d5"),
                new CornerRadii(20),
                new Insets(5, 5, 5, 5)
        );
        Background background = new Background(back);
        db.setBackground(background);
        return db;
    }

    /**
     * Generates a DialogBox based on given text and avatar for Duke output.
     *
     * @param text Text to be printed.
     * @param img Avatar to be displayed.
     * @return JavaFX DialogBox containing the given text and avatar.
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        BackgroundFill back = new BackgroundFill(
                Color.web("#c5dae3"),
                new CornerRadii(20),
                new Insets(5, 5, 5, 5)
        );
        Background background = new Background(back);
        db.setBackground(background);
        db.setTranslateX(0);
        db.dialog.setStyle("-fx-background-radius: 0px 10px 10px 10px");
        db.dialog.setTranslateX(5);
        db.dialog.setTextAlignment(TextAlignment.LEFT);
        return db;
    }
}
