package duke;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class DialogBoxUser extends DialogBox {

    /** Constructor for DialogBoxDuke.
     *
     * @param label label containing text
     * @param circle circle-shaped image
     */
    public DialogBoxUser(Label label, Circle circle) {
        super(label, circle);
        Color col = Color.rgb(190, 231, 223);
        CornerRadii corn = new CornerRadii(10);
        Insets insets = new Insets(-2, 1, -2, 0);
        Background background = new Background(
                new BackgroundFill(col, corn, insets));
        getText().setBackground(background);
    }

    /** Creates a new User DialogBox.
     *
     * @param label label containing text
     * @param circle circle-shaped image
     * @return a User DialogBox
     */
    public static DialogBox getUserDialog(Label label, Circle circle) {
        return new DialogBoxUser(label, circle);
    }
}
