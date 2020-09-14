package duke;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private Text dialog;
    @FXML
    private ImageView displayPicture;
    @FXML
    private TextFlow textContainer;

    final private String DUKE_DIALOG_CSS = "-fx-background-color: #CDEDFD; -fx-border-radius: 10; -fx-background-radius: 10; -fx-padding: 10;";

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
     * Also changes the colour of the dialog box.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
        textContainer.setStyle(DUKE_DIALOG_CSS);
    }

    /**
     * Returns the user's <code>DialogBox</code> that has both the text and user image.
     * @param text the user's text to be shown in the <code>DialogBox</code>.
     * @param img the user's image to be shown in the <code>DialogBox</code>.
     * @return a <code>DialogBox</code>.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    /**
     * Returns Duke's <code>DialogBox</code> that has both the text and Duke image.
     * @param text Duke's response text to be shown in the <code>DialogBox</code>.
     * @param img Duke's image to be shown in the <code>DialogBox</code>.
     * @return a <code>DialogBox</code>.
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        return db;
    }
}
