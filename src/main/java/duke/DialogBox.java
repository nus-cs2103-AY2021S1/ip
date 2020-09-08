package duke;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

public class DialogBox extends HBox {

    private Label text;
    private ImageView displayPicture;
    private Circle circle = new Circle(40);


    public DialogBox(Label l, Image iv, Image background) {
        text = l;
        circle.setFill(new ImagePattern(iv));
        text.setWrapText(true);

        // create a background image
        BackgroundImage backgroundimage = new BackgroundImage(background,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);

        // create Background
        Background backgroundImage = new Background(backgroundimage);

        // set background
        this.setBackground(backgroundImage);

        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(text, circle);

    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */

    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    public static DialogBox getUserDialog(Label l, Image iv, Image background) {
        return new DialogBox(l, iv, background);
    }

    public static DialogBox getDukeDialog(Label l, Image iv, Image background) {
        var db = new DialogBox(l, iv, background);
        db.flip();
        return db;
    }



}
