package ui;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.collections.FXCollections;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.collections.ObservableList;
import javafx.scene.Node;

/**
 * The dialog box
 */
public class DialogBox extends HBox {

    private Label text;
    private ImageView displayPicture;
    private final double WIDTH = 100.0;
    private final double HEIGHT = 100.0;

    /**
     * Constructs a new dialogBox
     * @param l [label]
     * @param iv [view of image]
     */
    public DialogBox(Label l, ImageView iv) {
        text = l;
        displayPicture = iv;

        text.setWrapText(true);
        displayPicture.setFitWidth(WIDTH);
        displayPicture.setFitHeight(HEIGHT);

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
     * Gets a dialogBox of duke
     * @param l the label
     * @param iv and image
     * @return a box
     */
    public static DialogBox getUserDialog(Label l, ImageView iv) {
        return new DialogBox(l, iv);
    }

    /**
     * Gets a dialogBox of duke
     * @param l the label
     * @param iv and image
     * @return a box
     */
    public static DialogBox getDukeDialog(Label l, ImageView iv) {
        var db = new DialogBox(l, iv);
        db.flip();
        return db;
    }
}

