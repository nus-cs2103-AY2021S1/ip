package duke;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;

public class DialogBox extends HBox {
    private static final String USER_BACKGROUND =
            "-fx-background-color: rgba(114, 205, 247, 0.5); -fx-background-radius: 15;";
    private static final String DUKE_BACKGROUND =
            "-fx-background-color: rgba(255, 168, 220, 0.5); -fx-background-radius: 15;";

    /**
     * Constructs a DialogBox.
     *
     * @param l The label of the DialogBox.
     * @param iv The image of the DialogBox.
     */
    public DialogBox(Label l, ImageView iv) {
        // Sets padding between items in the HBox
        super(10);

        l.setWrapText(true);
        l.setPadding(new Insets(5, 10, 5, 10));
        l.setFont(new Font("Consolas", 18));

        iv.setFitWidth(100.0);
        iv.setFitHeight(100.0);
        // Sets the image to be a circle
        final Circle clip = new Circle(50, 50, 50);
        iv.setClip(clip);

        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(l, iv);
        this.setPadding(new Insets(10));
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
     * Returns a user's dialog box.
     *
     * @param l The user's Label.
     * @param iv The user's ImageView.
     * @return DialogBox corresponding to the user.
     */
    public static DialogBox getUserDialog(Label l, ImageView iv) {
        // Need to setStyle separately as can't override once set
        l.setStyle(USER_BACKGROUND);
        return new DialogBox(l, iv);
    }

    /**
     * Returns Duke's dialog box.
     *
     * @param l Duke's Label.
     * @param iv The Duke's ImageView.
     * @return DialogBox corresponding to the Duke.
     */
    public static DialogBox getDukeDialog(Label l, ImageView iv) {
        l.setStyle(DUKE_BACKGROUND);
        var db = new DialogBox(l, iv);
        db.flip();
        return db;
    }
}
