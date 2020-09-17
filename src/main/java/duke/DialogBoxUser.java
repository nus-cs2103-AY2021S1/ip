package duke;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class DialogBoxUser extends DialogBox {

    public DialogBoxUser(Label l, Circle c) {
        super(l, c);
        Color col = Color.rgb(190,231,223);
        CornerRadii corn = new CornerRadii(10);
        Insets insets = new Insets(-2,1,-2,0);
        Background background = new Background(new BackgroundFill(col, corn, insets));
        text.setBackground(background);
    }

    public static DialogBox getUserDialog(Label l, Circle c) {
        return new DialogBoxUser(l, c);
    }
}
