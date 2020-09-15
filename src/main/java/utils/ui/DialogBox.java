package utils.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;

public class DialogBox extends HBox {

    private Label text;
    private ImageView displayPicture;

    /**
     * Instantiates a new Dialog box.
     *
     * @param l  the text label of the dialog
     * @param iv the image view of the sender
     */
    public DialogBox(Label l, ImageView iv) {
        text = l;
        displayPicture = iv;

        text.setWrapText(true);
        displayPicture.setFitWidth(84);
        displayPicture.setFitHeight(84);

        Circle circle = new Circle();
        circle.setCenterX(84);
        circle.setCenterY(42);
        circle.setRadius(42);

        iv.setClip(circle);

        text.setStyle("-fx-background-color: #7781FF; -fx-background-radius: 32;");
        text.setPadding(new Insets(12));

        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(text, displayPicture);
    }

    public static DialogBox getUserDialog(String input, Image userImage) {
        return new DialogBox(new Label(input), new ImageView(userImage));
    }
    public static DialogBox getDukeDialog(String input, Image userImage) {
        return new DialogBox(new Label(input), new ImageView(userImage)).flip();
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    public DialogBox flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);

        Circle circle = new Circle();

        circle.setCenterX(0);
        circle.setCenterY(42);
        circle.setRadius(42);
        displayPicture.setClip(circle);

        text.setStyle("-fx-background-color: white; -fx-background-radius: 32;");
        text.setPadding(new Insets(12));
        text.setMinHeight(Double.NEGATIVE_INFINITY);

        if (text.getText().startsWith("you n00b!")) {
            text.setStyle("-fx-background-color: red; -fx-background-radius: 32;");
        }
        return this;
    }

}
