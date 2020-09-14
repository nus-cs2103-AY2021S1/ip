package main.gui;

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
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

/**
 * Represents the dialog box of the user or stuff.
 * @author Joshua Liang XingYa
 * @author joshualiang.xy@gmail.com
 * @version v0.2
 * @since v0.2
 */
public class DialogBox extends HBox {
    private static final Insets DIALOG_BOX_PADDING = new Insets(10, 0, 10, 0);
    private static final Color DIALOG_COLOR = Color.rgb(255, 255, 255);
    private static final Insets DIALOG_PADDING = new Insets(5);
    private static final Background TEXT_BOX = new Background(
            new BackgroundFill(
                    Color.rgb(14, 122, 254),
                    new CornerRadii(5),
                    Insets.EMPTY
            )
    );

    private DialogBox(Label text, ImageView displayPicture) {
        text.setWrapText(true);
        text.setPadding(DIALOG_PADDING);
        text.setTextFill(DIALOG_COLOR);
        text.setBackground(TEXT_BOX);
        displayPicture.setFitWidth(100.0);
        displayPicture.setFitHeight(100.0);

        this.setAlignment(Pos.TOP_RIGHT);
        this.setPadding(DIALOG_BOX_PADDING);
        this.getChildren().addAll(text, displayPicture);
    }

    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    /**
     * Constructs a DialogBox instance with user input and display picture of user.
     * @param text the user input.
     * @param displayPicture the picture of the user.
     * @return a DialogBox instance with user input and display picture of user.
     */
    public static DialogBox getUserDialog(Label text, ImageView displayPicture) {
        return new DialogBox(text, displayPicture);
    }

    /**
     * Constructs a DialogBox instance with dialog and display picture of Stuff.
     * @param text the dialog of Stuff.
     * @param displayPicture the picture of Stuff.
     * @return a DialogBox instance with dialog and display picture of Stuff.
     */
    public static DialogBox getStuffDialog(Label text, ImageView displayPicture) {
        DialogBox db = new DialogBox(text, displayPicture);
        db.flip();
        return db;
    }
}
