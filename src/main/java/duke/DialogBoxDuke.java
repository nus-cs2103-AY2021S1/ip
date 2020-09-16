package duke;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class DialogBoxDuke extends DialogBox {
    private final Color col = Color.rgb(238,160,178);
    private final CornerRadii corn = new CornerRadii(10);
    private final Insets insets = new Insets(-2,0,-2,2);
    private final Background background = new Background(new BackgroundFill(col, corn, insets));

    public DialogBoxDuke(Label l, Circle c) {
        super(l, c);
        text.setBackground(background);
    }

    public static DialogBox getDukeDialog(Label l, Circle c) {
        DialogBox db = new DialogBoxDuke(l, c);
        db.flip();
        return db;
    }
}
