package duke;

import java.io.IOException;
import java.util.Collections;

import duke.response.Response;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;

public class DialogBox extends HBox {
    private static final String ERROR_HEX_COLOR = "#ff0000";
    private static final String CLASS_BG_COLOR = "-fx-background-color: ";
    private static final String FXML_PATH = "/view/DialogBox.fxml";
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource(FXML_PATH));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        displayPicture.setImage(img);
    }

    @FXML
    void initialize() {
        double height = 100.0;
        double width = 100.0;
        displayPicture.setFitWidth(width);
        displayPicture.setFitHeight(height);
        Circle clip = new Circle(width / 2, height / 2, height / 2);
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

    /**
     * Creates a dialog box to display the user's input.
     *
     * @param text String text input of user.
     * @param img Image image of user.
     * @return DialogBox a dialog box of the user's input.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    /**
     * Creates a dialog box to display duke's response.
     * Changes dialog to red for an error response.
     *
     * @param resp Response text response from Duke.
     * @param img Image image of Duke.
     * @return DialogBox a dialog box of the Duke's response.
     */
    public static DialogBox getDukeDialog(Response resp, Image img) {
        DialogBox db = new DialogBox(resp.getMessage(), img);
        if (resp.isError()) {
            String dialogStyle = db.dialog.getStyle();
            int startIndex = dialogStyle.indexOf(CLASS_BG_COLOR);
            int endIndex = dialogStyle.substring(startIndex).indexOf(";");
            String updatedStyle = dialogStyle.substring(0, startIndex + CLASS_BG_COLOR.length())
                    + ERROR_HEX_COLOR + dialogStyle.substring(endIndex);
            db.dialog.setStyle(updatedStyle);
        }
        db.flip();
        return db;
    }
}
