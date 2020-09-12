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

    private DialogBox(String text, Image img) {
        assert !text.equals("") && img != null : "A null label or image was provided";
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
        Color color = Color.LIGHTBLUE;
        this.setBackground(new Background(
                new BackgroundFill(color, new CornerRadii(10),
                        new Insets(5,5,5,5))));
    }

    public static DialogBox getErrorBox(String text, Image img){
        DialogBox dialog = getDukeDialog(new Label(text), img);
        dialog.getChildren().get(1).setStyle("-fx-background-color: #E4572E");
        return dialog;
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

    public static DialogBox getUserDialog(Label text, Image img) {
        assert text != null && img != null : "A null label or image was provided";
        DialogBox dialog = new DialogBox(text.getText(), img);
        dialog.getChildren().get(0).setStyle("-fx-background-color: #0066ff");
        dialog.applyCss();
        return dialog;
    }

    public static DialogBox getDukeDialog(Label text, Image img) {
        assert text != null && img != null : "A null label or image was provided";
        DialogBox db = new DialogBox(text.getText(), img);
        db.getChildren().get(0).setStyle("-fx-background-color : #66ff66");
        db.applyCss();
        db.flip();
        return db;
    }
}
