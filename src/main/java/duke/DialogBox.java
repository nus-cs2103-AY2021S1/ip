package duke;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class DialogBox extends HBox {

    private Label text;
    private ImageView displayPicture;

    /**
     * Creates and initialises a DialogBox object with preset styles.
     *
     * @param text Label a Label Node with the relevant dialog message.
     * @param displayPicture ImageView the display picture of the user.
     */
    public DialogBox(Label text, ImageView displayPicture) {
        this.text = text;
        this.displayPicture = displayPicture;
        setDialogBoxStyle();
    }

    private void setDialogBoxStyle() {
        text.setWrapText(true);
        text.setPadding(new Insets(10, 10, 10, 10));
        text.setTextFill(Color.web("#AAAAAA"));
        double height = 100.0;
        double width = 100.0;
        displayPicture.setFitWidth(width);
        displayPicture.setFitHeight(height);
        this.setPadding(new Insets(10, 10, 10, 10));
        Circle clip = new Circle(width / 2, height / 2, height / 2);
        displayPicture.setClip(clip);

        this.setBackground(new Background(new BackgroundFill(Color.web("#424242"), CornerRadii.EMPTY, Insets.EMPTY)));
        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(text, displayPicture);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    /**
     * Creates a dialog box to display the user's input.
     *
     * @param l Label a Label node with message of the user.
     * @param iv ImageView image of user.
     * @return DialogBox a dialog box of the user's input.
     */
    public static DialogBox getUserDialog(Label l, ImageView iv) {
        return new DialogBox(l, iv);
    }

    /**
     * Creates a dialog box to display duke's response.
     *
     * @param l Label a Label node with the response from Duke.
     * @param iv ImageView image of Duke.
     * @return DialogBox a dialog box of the Duke's response.
     */
    public static DialogBox getDukeDialog(Label l, ImageView iv) {
        DialogBox db = new DialogBox(l, iv);
        db.flip();
        return db;
    }
}
