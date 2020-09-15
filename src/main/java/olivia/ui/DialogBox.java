package olivia.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;

public class DialogBox extends HBox {

    private Label text;
    private ImageView displayPicture;

    /**
     * Constructor that creates a DialogBox object.
     *
     * @param label the Label associated with the DialogBox object
     * @param imageview an Image associated with the DialogBox
     *                  object wrapped in an ImageView object
     */

    public DialogBox(Label label, ImageView imageview) {
        text = label;
        displayPicture = imageview;
        imageview.setClip(new Circle(50, 50, 50));

        text.setWrapText(true);
        displayPicture.setFitWidth(100.0);
        displayPicture.setFitHeight(100.0);

        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(text, displayPicture);
    }

    private DialogBox flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
        return this;
    }

    public static DialogBox getUserDialog(Label l, ImageView iv) {
        return new DialogBox(l, iv);
    }

    public static DialogBox getOliviaDialog(Label l, ImageView iv) {
        return new DialogBox(l, iv).flip();
    }

}
