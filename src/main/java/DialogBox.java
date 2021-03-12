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

import java.io.IOException;
import java.util.Collections;

/**@@author SE-EDU student project guide, a sub-project of the se-education.org.
 * Point of contact: Damith C. Rajapakse https://www.comp.nus.edu.sg/~damithch/
 *
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    /**@@author SE-EDU student project guide, a sub-project of the se-education.org.
     * Point of contact: Damith C. Rajapakse https://www.comp.nus.edu.sg/~damithch/
     *
     * Creates a DialogBox for GUI conversation between user and Duke.
     *
     * @param img Image to add in DialogBox.
     * @param text to display text in DialogBox.
     *
     * @throws IOException
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

        //Set text
        dialog.setText(text);
        Background dialogBackground = new Background(
                new BackgroundFill(Color.rgb(184, 224, 255, 1),
                        new CornerRadii(5.0), new Insets(-3.0)));
        dialog.setBackground(dialogBackground);

        //Set image
        displayPicture.setImage(img);
        Circle circleClip = new Circle(50.0, 50.0, 50.0);
        displayPicture.setClip(circleClip);
    }

    /**@@author SE-EDU student project guide, a sub-project of the se-education.org.
     * Point of contact: Damith C. Rajapakse https://www.comp.nus.edu.sg/~damithch/
     *
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    /**@@author SE-EDU student project guide, a sub-project of the se-education.org.
     * Point of contact: Damith C. Rajapakse https://www.comp.nus.edu.sg/~damithch/
     *
     * Uses a factory method to create DialogBox objects
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    /**@@author SE-EDU student project guide, a sub-project of the se-education.org.
     * Point of contact: Damith C. Rajapakse https://www.comp.nus.edu.sg/~damithch/
     *
     * Uses a factory method to create flipped DialogBox objects
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        return db;
    }
}
