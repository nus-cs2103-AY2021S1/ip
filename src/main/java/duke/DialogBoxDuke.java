package duke;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class DialogBoxDuke extends DialogBox {
    public DialogBoxDuke(Label l, Circle c) {
        super(l, c);
        Color color = Color.rgb(238,160,178);
        CornerRadii corn = new CornerRadii(10);
        Insets insets = new Insets(-2,0,-2,3);
        Background background = new Background(new BackgroundFill(color, corn, insets));
        text.setBackground(background);
    }

    public static DialogBox getDukeDialog(Label l, Circle c) {
        DialogBoxDuke db = new DialogBoxDuke(l, c);
        db.flip();
        return db;
    }
}
