package duke;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class DialogBoxUser extends DialogBox {
    private final Color col = Color.rgb(190,231,223);
    private final CornerRadii corn = new CornerRadii(10);
    private final Insets insets = new Insets(-2,1,-2,0);
    private final Background background = new Background(new BackgroundFill(col, corn, insets));

    public DialogBoxUser(Label l, Circle c) {
        super(l, c);
        text.setBackground(background);
    }

    public static DialogBox getUserDialog(Label l, Circle c) {
        return new DialogBoxUser(l, c);
    }
}
