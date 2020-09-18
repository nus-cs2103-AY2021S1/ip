package olivia.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;

public class DialogBox extends HBox {

    /** default spacing between dialog and text box */
    private static final double SPACING = 10;

    private TextBox text;
    private ImageView displayPicture;

    private DialogBox(String text, ImageView imageview, boolean left) {
        this.text = left
                ? TextBox.leftwardTextBox(text)
                : TextBox.rightwardTextBox(text);

        displayPicture = imageview;
        imageview.setClip(new Circle(50, 50, 50));
        displayPicture.setFitWidth(100.0);
        displayPicture.setFitHeight(100.0);
        getChildren().addAll(this.text, displayPicture);

        setAlignment(Pos.TOP_RIGHT);
        setSpacing(SPACING);
    }

    private DialogBox flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
        return this;
    }

    public static DialogBox getUserDialog(String message, ImageView iv) {
        return new DialogBox(message, iv, false);
    }

    public static DialogBox getOliviaDialog(String message, ImageView iv) {
        return new DialogBox(message, iv, true).flip();
    }

}
