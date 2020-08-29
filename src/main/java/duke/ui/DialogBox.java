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
    private static final Insets BOX_PADDING = new Insets(10, 2 , 10 , 2);
    private static final Insets TEXT_PIC_PADDING = new Insets(0, 0, 0 , 10);

    /**
     * Creates a dialog box in GUI.
     *
     * @param l Label to be displayed.
     * @param iv Imageview to be displayed
     */
    public DialogBox(Label l, ImageView iv) {
        Circle clipCircle = new Circle(50, 50, 50);

        l.setWrapText(true);
        l.setPadding(DialogBox.TEXT_PIC_PADDING);
        iv.setFitWidth(100.0);
        iv.setFitHeight(100.0);
        iv.setClip(clipCircle);

        this.setPadding(DialogBox.BOX_PADDING);
        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(l, iv);
    }

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
        DialogBox db = new DialogBox(l, iv);
        db.flip();
        return db;
    }
}
