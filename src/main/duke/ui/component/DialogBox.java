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

    private Label text;
    private ImageView displayPicture;

    /**
     * Constructor for a DialogBox
     * @param l the Label containing the text to be displayed
     * @param iv the ImageView containing the image to use as the sender's picture
     */
    public DialogBox(Label l, ImageView iv) {
        text = l;
        displayPicture = iv;

        text.setWrapText(true);
        displayPicture.setFitWidth(100.0);
        displayPicture.setFitHeight(100.0);

    }

    /**
     * Constructor for a DialogBox with the Duke as the sender
     * @param l the Label containing the text to be displayed
     * @param iv  the ImageView containing the image to use as Duke's picture
     * @return
     */
    public static DialogBox dukeDialog(Label l, ImageView iv) {
        l.setBackground(new Background(new BackgroundFill(Color.rgb(179, 141, 151), new CornerRadii(3.0),
                new Insets(0.0))));
        DialogBox db = new DialogBox(l, iv);
        db.setAlignment(Pos.TOP_LEFT);
        db.getChildren().addAll(db.displayPicture, db.text);
        return db;
    }


    /**
     * Constructor for a DialogBox with the User as the sender
     * @param l the Label containing the text to be displayed
     * @param iv  the ImageView containing the image to use as User's picture
     * @return
     */
    public static DialogBox userDialog(Label l, ImageView iv) {
        l.setBackground(new Background(new BackgroundFill(Color.rgb(213, 172, 169), new CornerRadii(3.0),
                new Insets(0.0))));
        DialogBox db = new DialogBox(l, iv);
        db.setAlignment(Pos.TOP_RIGHT);
        db.getChildren().addAll(db.text, db.displayPicture);
        return db;
    }


}
