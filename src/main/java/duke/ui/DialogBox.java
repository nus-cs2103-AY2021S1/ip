package duke.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;


public class DialogBox extends HBox {
    private final static Insets BOX_PADDING = new Insets(10,2,10,2);
    private final static Insets TEXT_PIC_PADDING = new Insets(0,0,0,10);

    private Label text;
    private ImageView displayPicture;

    public DialogBox(Label l, ImageView iv) {
        Circle clipCircle = new Circle(50,50,50);

        text = l;
        displayPicture = iv;

        text.setWrapText(true);
        text.setPadding(DialogBox.TEXT_PIC_PADDING);
        displayPicture.setFitWidth(100.0);
        displayPicture.setFitHeight(100.0);
        displayPicture.setClip(clipCircle);

        this.setPadding(DialogBox.BOX_PADDING);
        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(text,displayPicture);
    }

    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    public static DialogBox getUserDialog(Label l, ImageView iv) {
        return new DialogBox(l,iv);
    }

    public static DialogBox getDukeDialog(Label l, ImageView iv) {
        DialogBox db = new DialogBox(l,iv);
        db.flip();
        return db;
    }
}
