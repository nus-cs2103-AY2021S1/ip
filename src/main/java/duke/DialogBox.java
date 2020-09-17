package duke;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;

public class DialogBox extends HBox {
    protected Label text;
    private Circle imageHolder;
    private Font font = new Font("Baskerville", 14);

    public DialogBox(Label l, Circle c) {
        text = l;
        imageHolder = c;

        text.setMinWidth(35);
        text.setMinHeight(30);
        text.setFont(font);
        text.setAlignment(Pos.CENTER);
        text.setWrapText(true);

        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(text, imageHolder);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    protected void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }
}
