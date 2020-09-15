package duke.ui;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

/**
 * Represents the dialog box that is shown to the user which contains the dialog and display picture.
 */
public class DialogBox extends HBox {

    /**
     * A label instance of the dialog box.
     */
    @FXML
    private Label dialog;

    /**
     * The image view of the display picture.
     */
    @FXML
    private Circle displayPicture;

    /**
     * Constructs a dialog box that stores the text and display picture.
     *
     * @param text The dialog text to be stored.
     * @param image The image of the display picture.
     */
    private DialogBox(String text, Image image) {
        super(20);

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);

        // Add padding to text
        dialog.setPadding(new Insets(8));

        // Add background color to text box for user
        setBackgroundColor(Color.PINK);

        displayPicture.setFill(new ImagePattern(image));
    }

    private void setBackgroundColor(Color color) {
        CornerRadii radii = new CornerRadii(10);
        dialog.setBackground(new Background(new BackgroundFill(color, radii, Insets.EMPTY)));
    }

    /**
     * Flips the dialog box such that the image is on the left and text on the right.
     */
    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    /**
     * Returns a DialogBox object storing the user's input and display picture.
     *
     * @param input The text representing the user input.
     * @param image The image of the display picture.
     * @return DialogBox object.
     */
    public static DialogBox getUserDialog(String input, Image image) {
        return new DialogBox(input, image);
    }

    /**
     * Returns a DialogBox object storing Duke's response and display picture.
     *
     * @param response The text representing Duke's response.
     * @param image The image of the display picture.
     * @return DialogBox object.
     */
    public static DialogBox getDukeDialog(String response, Image image) {
        var db = new DialogBox(response, image);
        db.flip();
        db.setBackgroundColor(Color.LIGHTBLUE);
        return db;
    }
}

