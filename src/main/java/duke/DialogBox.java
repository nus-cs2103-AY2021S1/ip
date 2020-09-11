package duke;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

/**
 * Class to represent Text Box in the Anchor pane GUI
 */
public class DialogBox extends HBox {

    private Label text;
    private Image displayPicture;
    private Circle circle;

    /**
     * Creates a DialogBox.
     * @param l Contents of the dialog box
     * @param iv Display picture of dialog box
     */
    public DialogBox(Label l, Image iv) {
        text = l;
        displayPicture = iv;

        circle = new Circle();
        circle.setCenterX(100.0f);
        circle.setCenterY(100.0f);
        circle.setRadius(50.0f);

        text.setWrapText(true);
        // displayPicture.setFitWidth(75.0);
        // displayPicture.setFitHeight(75.0);

        circle.setFill(new ImagePattern(displayPicture));

        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(text, circle);
        this.setSpacing(10.0); // add padding between profile pic and text
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
     * Returns a user Dialog Box
     * @param l Input from the user
     * @param iv Picture of the user
     * @return Dialog box of user's input
     */
    public static DialogBox getUserDialog(Label l, Image iv) {
        var db = new DialogBox(l, iv);
        db.setBackground(new Background(new BackgroundFill(Color.CYAN, null, null)));
        return db;
    }

    /**
     * Returns a Duke Dialog Box
     * @param l Input from Duke
     * @param iv Picture of Duke
     * @return Dialog Box of duke's response
     */
    public static DialogBox getDukeDialog(Label l, Image iv) {
        var db = new DialogBox(l, iv);
        db.flip();
        db.setBackground(new Background(new BackgroundFill(Color.DARKORANGE, null, null)));
        return db;
    }
}
