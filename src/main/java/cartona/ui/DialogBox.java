package cartona.ui;

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

/**.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's (bot or user) face and a
 * label containing text from the speaker.
 *
 * @author Jaya Rengam
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

        dialog.setText(text);
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

    /**
     * Returns a formatted DialogBox that is used to show user text
     *
     * @param text input text from the user
     * @param img image of the user
     */
    public static DialogBox getUserDialog(String text, Image img) {
        DialogBox userDialog = new DialogBox(text, img);
        Background dialogBackground = new Background(
                new BackgroundFill(Color.rgb(255, 240, 0, 0.5),
                new CornerRadii(10.0), new Insets(5.0)));

        userDialog.dialog.setBackground(dialogBackground);

        return userDialog;
    }

    /**
     * Returns a formatted DialogBox that is used to show text returned from Cartona
     *
     * @param text text from the program to be displayed
     * @param img image of Cartona
     */
    public static DialogBox getCartonaDialog(String text, Image img) {
        var cartonaDialog = new DialogBox(text, img);
        cartonaDialog.flip();
        Background cartonaBackground = new Background(
                new BackgroundFill(Color.rgb(160, 200, 190, 0.5),
                        new CornerRadii(2.0), new Insets(10.0)));

        cartonaDialog.dialog.setBackground(cartonaBackground);
        return cartonaDialog;
    }
}
