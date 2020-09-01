package duke.main;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.geometry.Insets;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;


public class DialogBox extends HBox {
    private Label text;
    private ImageView displayPicture;

    public DialogBox(Label l, ImageView iv) {
        this.text = l;
        this.displayPicture = iv;

        text.setWrapText(true);
        displayPicture.setFitHeight(100.0);
        displayPicture.setFitWidth(100.0);

        l.setPadding(new Insets(0, 10, 0, 0));
        this.setAlignment(Pos.TOP_RIGHT);
        this.setPadding(new Insets(10, 0, 10, 0));
        this.getChildren().addAll(text, displayPicture);
    }

    private void setBackgroundColor(Color color) {
        this.setBackground(new Background(new BackgroundFill(color,null, null)));
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
        DialogBox db = new DialogBox(l, iv);
        db.setBackgroundColor(Color.GREEN);
        return db;
    }

    public static DialogBox getDukeDialog(Label l, ImageView iv) {
        DialogBox dukeBox = new DialogBox(l, iv);
        dukeBox.flip();
        dukeBox.text.setPadding(new Insets(0, 0, 0, 10));
        dukeBox.setBackgroundColor(Color.YELLOW);
        return dukeBox;
    }
}
