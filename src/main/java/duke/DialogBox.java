package duke;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.shape.Circle;


public class DialogBox extends HBox {

    private Label text;
    private ImageView displayPicture;

    public DialogBox(Label l, ImageView iv) {
        text = l;
        iv.setClip(new Circle(60,60,60));
        displayPicture = iv;

        text.setWrapText(true);
        displayPicture.setFitWidth(120.0);
        displayPicture.setFitHeight(120.0);
        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(text, displayPicture);
        this.setSpacing(20);
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
        DialogBox userDialog = new DialogBox(l, iv);
        userDialog.setStyle("-fx-background-color: #f1f3f8;");
        userDialog.setPadding(new Insets(15,10,15,0));
        return userDialog;
    }

    public static DialogBox getDukeDialog(Label l, ImageView iv) {
        var db = new DialogBox(l, iv);
        db.flip();
        db.setStyle("-fx-background-color: #f6bed6;");
        db.setPadding((new Insets(15, 0, 15, 10)));
        return db;
    }
}
