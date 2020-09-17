package duke;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;

@SuppressWarnings("CheckStyle")
public class DialogBox extends HBox {
    @SuppressWarnings("CanBeFinal")
    // The label containing text to be added in DialogBox.
    private Label text;

    /** Constructor for DialogBox.
     *
     * @param label label containing text
     * @param circle circle-shaped image
     */
    public DialogBox(Label label, Circle circle) {
        text = label;

        @SuppressWarnings("SpellCheckingInspection")
        Font font = new Font("Baskerville", 14);
        text.setMinWidth(35);
        text.setMinHeight(30);
        text.setFont(font);
        text.setAlignment(Pos.CENTER);
        text.setWrapText(true);

        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(text, circle);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and
     * text on the right.
     */
    protected void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections
                .observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    /**
     * Returns the label instance named text;
     */
    protected Label getText() {
        return text;
    }
}
