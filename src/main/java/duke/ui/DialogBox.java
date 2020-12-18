package duke.ui;

import java.io.IOException;
import java.util.Collections;

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
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {
    private static final String DUKE_DIALOG_COLOUR = "#D9D9D9";
    private static final String USER_DIALOG_COLOUR = "#95EC69";
    private static final String DUKE_EXCEPTION_DIALOG_COLOUR = "#FFA199";

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
        dialog.setWrapText(true);
        displayPicture.setImage(img);
        Circle circle = new Circle(40);
        circle.setStroke(Color.WHITE);
        circle.setCenterX(displayPicture.getFitWidth() / 2);
        circle.setCenterY(displayPicture.getFitHeight() / 2);
        displayPicture.setClip(circle);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.CENTER_LEFT);
    }

    private void setDialogColour(String colour) {
        String existingStyle = this.dialog.styleProperty().getValue();
        this.dialog.setStyle(String.format("%s -fx-background-color: %s;", existingStyle, colour));
    }

    public static DialogBox getUserDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.setAlignment(Pos.CENTER_RIGHT);
        db.setDialogColour(USER_DIALOG_COLOUR);
        return db;
    }

    public static DialogBox getDukeDialog(String text, Image img, boolean isError) {
        var db = new DialogBox(text, img);
        db.flip();
        if (isError) {
            db.setDialogColour(DUKE_EXCEPTION_DIALOG_COLOUR);
        } else {
            db.setDialogColour(DUKE_DIALOG_COLOUR);
        }
        db.setFillHeight(true);
        return db;
    }
}
