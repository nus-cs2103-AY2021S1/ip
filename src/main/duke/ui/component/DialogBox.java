package duke.ui.component;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class DialogBox extends HBox {

    private Label text;
    private ImageView displayPicture;

    public DialogBox(Label l, ImageView iv) {
        text = l;
        displayPicture = iv;

        text.setWrapText(true);
        displayPicture.setFitWidth(100.0);
        displayPicture.setFitHeight(100.0);

    }

    public static DialogBox dukeDialog(Label l, ImageView iv) {
        DialogBox db = new DialogBox(l, iv);
        db.setAlignment(Pos.TOP_LEFT);
        db.getChildren().addAll(db.displayPicture, db.text);
        return db;
    }
    public static DialogBox userDialog(Label l, ImageView iv) {
        DialogBox db = new DialogBox(l, iv);
        db.setAlignment(Pos.TOP_RIGHT);
        db.getChildren().addAll(db.text, db.displayPicture);
        return db;
    }


}
