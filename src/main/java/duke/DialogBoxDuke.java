package duke;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class DialogBoxDuke extends DialogBox {
    /** Constructor for DialogBoxDuke.
     *
     * @param label label containing text
     * @param circle circle-shaped image
     */
    public DialogBoxDuke(Label label, Circle circle) {
        super(label, circle);
        Color color = Color.rgb(238, 160, 178);
        CornerRadii corn = new CornerRadii(10);
        Insets insets = new Insets(-2, 0, -2, 3);
        Background background = new Background(
                new BackgroundFill(color, corn, insets));
        getText().setBackground(background);
    }

    /** Creates a new Duke DialogBox.
     *
     * @param label label containing text
     * @param circle circle-shaped image
     * @return a Duke DialogBox
     */
    public static DialogBox getDukeDialog(Label label, Circle circle) {
        DialogBoxDuke db = new DialogBoxDuke(label, circle);
        db.flip();
        return db;
    }
}
