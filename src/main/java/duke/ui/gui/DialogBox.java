package duke.ui.gui;

import java.io.IOException;
import java.util.Collections;

import duke.utils.UtilFunction;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private Label name;
    @FXML
    private Circle displayPicture;
    @FXML
    private VBox nameAndDialog;
    @FXML
    private VBox timeStampContainer;
    @FXML
    private Label timeStamp;

    private DialogBox(String text, Image img, boolean isUser) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(isUser) {
            name.setText("You");
            nameAndDialog.setAlignment(Pos.TOP_RIGHT);
            dialog.setStyle("-fx-background-color: #66ff33");
        } else {
            name.setText("Duke");
            dialog.setStyle("-fx-background-color: #ffffff");
        }

        dialog.setText(text);
        dialog.setPrefWidth(text.length() > 40 ? 400 : text.length() * 10 + 10 );
        timeStamp.setText(UtilFunction.getCurrentTime());
        timeStampContainer.setPadding(new Insets(UtilFunction.getPadding(text), 0, 0, 0));
        //move to fxml
        displayPicture.setRadius(20);
        displayPicture.setFill(new ImagePattern(img));

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
        return new DialogBox(text, img, true);
    }

    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img, false);
        db.flip();
        return db;
    }
}