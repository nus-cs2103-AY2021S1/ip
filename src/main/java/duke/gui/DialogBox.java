package duke.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * Represents a dialog box that is displayed to the user in the GUI.
 */
public class DialogBox extends HBox {

    private Label text;
    private ImageView displayPicture;

    /**
     * Creates an instance of a dialog box with the appropriate
     * text and display picture.
     * @param l Label containing text to be displayed.
     * @param iv ImageView to be displayed next to text.
     */
    public DialogBox(Label l, ImageView iv) {
        text = l;
        displayPicture = iv;

        text.setWrapText(true);
        displayPicture.setFitWidth(100.0);
        displayPicture.setFitHeight(100.0);

        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(text, displayPicture);
    }

    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    /**
     * Creates a dialog box for the user input.
     * @param l Label containing the user input.
     * @param iv ImageView of user display picture.
     * @return User input dialog box.
     */
    public static DialogBox getUserDialog(Label l, ImageView iv) {
        var db = new DialogBox(l, iv);
        db.setStyle("-fx-background-radius: 20px; -fx-background-color: #F7F07B;");
        return db;
    }

    /**
     * Creates a dialog box for the Duke response.
     * @param l Label containing Duke's response.
     * @param iv ImageView of Duke display picture.
     * @return Duke output dialog box.
     */
    public static DialogBox getDukeDialog(Label l, ImageView iv) {
        var db = new DialogBox(l, iv);
        db.flip();
        db.setStyle("-fx-background-radius: 20px; -fx-background-color: #35C9DD;");
        return db;
    }
}
