package duke.ui.component;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class DialogBox extends HBox {


    private final Label text;
    private final ImageView displayPicture;


    /**
     * Constructor for a DialogBox.
     * @param l the Label containing the text to be displayed.
     * @param iv the ImageView containing the Image to be displayed.
     */
    public DialogBox(Label l, ImageView iv) {
        text = l;
        displayPicture = iv;

        text.setWrapText(true);
        displayPicture.setFitWidth(100.0);
        displayPicture.setFitHeight(100.0);

    }

    /**
     * Constructor method for a DialogBox sent by the Duke.
     * @param l the Label containing the text to be displayed.
     * @param iv the ImageView containing the Image to be displayed.
     * @return a DialogBox with the Duke's image and alignment.
     */
    public static DialogBox dukeDialog(Label l, ImageView iv) {
        l.setBackground(new Background(new BackgroundFill(Color.rgb(176, 163, 212), new CornerRadii(3.0),
                new Insets(0.0))));
        DialogBox db = new DialogBox(l, iv);
        db.setAlignment(Pos.TOP_LEFT);
        db.getChildren().addAll(db.displayPicture, db.text);
        db.setBackground(new Background(new BackgroundFill(Color.rgb(86, 85, 84),
                new CornerRadii(0.0), new Insets(-10.0))));
        return db;
    }

    /**
     * Constructor method for a DialogBox sent by the User.
     * @param l the Label containing the text to be displayed.
     * @param iv the ImageView containing the Image to be displayed.
     * @return a DialogBox with the User's image and alignment.
     */
    public static DialogBox userDialog(Label l, ImageView iv) {
        l.setBackground(new Background(new BackgroundFill(Color.rgb(153, 178, 221), new CornerRadii(3.0),
                new Insets(0.0))));
        DialogBox db = new DialogBox(l, iv);
        db.setAlignment(Pos.TOP_RIGHT);
        db.getChildren().addAll(db.text, db.displayPicture);
        db.setBackground(new Background(new BackgroundFill(Color.rgb(103, 102, 101),
                new CornerRadii(0.0), new Insets(-10.0))));
        return db;
    }


}
