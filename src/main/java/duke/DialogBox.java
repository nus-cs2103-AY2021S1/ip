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


public class DialogBox extends HBox{
    private Label text;
    private ImageView displayPicture;

    public DialogBox(Label l, ImageView iv) {
        text = l;
        displayPicture = iv;

        text.setWrapText(true);

        displayPicture.setFitWidth(75.0);
        displayPicture.setFitHeight(75.0);

        final Circle clip = new Circle(37.5, 37.5, 37.5);
        displayPicture.setClip(clip);

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

    public static DialogBox getUserDialog(Label l, ImageView iv) {
        DialogBox dialogBox = new DialogBox(l, iv);
        dialogBox.setSpacing(10.0);
        dialogBox.setPadding(new Insets(0, 0, 0, 85.0));
        return dialogBox;
    }

    public static DialogBox getDukeDialog(Label l, ImageView iv) {
        DialogBox dialogBox = new DialogBox(l, iv);
        dialogBox.setSpacing(10.0);
        dialogBox.flip();
        dialogBox.setPadding(new Insets(0, 85, 0, 0));
        return dialogBox;
    }
}
