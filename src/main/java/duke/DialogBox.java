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
     * Initializes DialogBox object.
     *
     * @param l Label used to make DialogBox.
     * @param iv ImageView used to make DialogBox.
     * @param type User type.
     */
    public DialogBox(Label l, ImageView iv, String type) {
        text = l;
        displayPicture = iv;

        text.setWrapText(true);
        displayPicture.setFitWidth(100.0);
        displayPicture.setFitHeight(100.0);
        displayPicture.setClip(new Circle(50, 50, 50));

        if (type.equals("user")) {
            this.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, new CornerRadii(5.0),
                    new Insets(1, 1, 1, 1))));
        } else if (type.equals("duke")) {
            this.setBackground(new Background(new BackgroundFill(Color.LIGHTPINK, new CornerRadii(5.0),
                    new Insets(1, 1, 1, 1))));
        }

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
     * Gets the dialog box of the user.
     *
     * @param l User Label.
     * @param iv User Imageview.
     * @return User DialogBox.
     */
    public static DialogBox getUserDialog(Label l, ImageView iv) {
        return new DialogBox(l, iv, "user");
    }

    /**
     * Gets the dialog box of Duke.
     *
     * @param l Duke Label.
     * @param iv Duke Imageview.
     * @return Duke DialogBox.
     */
    public static DialogBox getDukeDialog(Label l, ImageView iv) {
        var db = new DialogBox(l, iv, "duke");
        db.flip();
        return db;
    }
}
