package duke;

import javafx.geometry.Pos;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Background;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DialogBox extends HBox {


    public DialogBox(Label l, Image iv, Image background) {
        Circle circle = new Circle(40);
        circle.setFill(new ImagePattern(iv));
        l.setWrapText(true);

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
        this.getChildren().addAll(l, circle);
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
