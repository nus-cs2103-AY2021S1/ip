package duke;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class DialogBox extends HBox {

    private Label text;
    private ImageView displayPicture;

    /**
     * DialogBox constructor which generates a dialogbox with text and an image
     * @param l the label
     * @param iv the image
     */
    public DialogBox(Label l, ImageView iv) {
        super(5);
        text = l;
        displayPicture = iv;
        text.setWrapText(true);
        displayPicture.setFitWidth(100.0);
        displayPicture.setFitHeight(100.0);
        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(text, displayPicture);
    }

    /**
     * DialogBox constructor which generates a dialogbox with the logo
     * @param iv the logo
     */
    public DialogBox(ImageView iv) {
        super(5);
        displayPicture = iv;
        displayPicture.setFitWidth(328 / 1.5);
        displayPicture.setFitHeight(117 / 1.5);
        this.setAlignment(Pos.BOTTOM_CENTER);
        this.getChildren().addAll(displayPicture);
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

    public static DialogBox getUserDialog(Label l, ImageView iv) {
        return new DialogBox(l, iv);
    }

    public static DialogBox getDukeDialog(Label l, ImageView iv) {
        var db = new DialogBox(l, iv);
        db.flip();
        return db;
    }
}
