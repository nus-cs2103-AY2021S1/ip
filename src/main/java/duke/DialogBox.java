package duke;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

public class DialogBox extends HBox {

    /**
     * Constructs a dialog box that stores the text and display picture.
     * @param label The label that stores the text.
     * @param image The image of the display picture.
     */
    public DialogBox(Label label, Image image) {
        super(20);
        label.setWrapText(true);
        label.setPadding(new Insets(10));

        // Add background color to text box
        Color color = Color.PINK;
        CornerRadii radii = new CornerRadii(10);
        label.setBackground(new Background(new BackgroundFill(color, radii, Insets.EMPTY)));

        // Clips display picture into a circular view
        Circle displayPicture = new Circle(40);
        ImagePattern imagePattern = new ImagePattern(image);
        displayPicture.setFill(imagePattern);

        this.setAlignment(Pos.TOP_RIGHT);

        // Add padding between each ImageView and its label
        this.setPadding(new Insets(10));
        this.getChildren().addAll(label, displayPicture);
    }

    /**
     * Flips the dialog box such that the image is on the left and text on the right.
     */
    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    /**
     * Returns a DialogBox object storing the label and the display picture.
     * @param label The label that stores the text.
     * @param image The image of the display picture.
     * @return DialogBox object.
     */
    public static DialogBox getUserDialog(Label label, Image image) {
        return new DialogBox(label, image);
    }

    /**
     * Returns a DialogBox object after switching the position of the label and display picture.
     * @param label The label that stores the text.
     * @param image The image of the display picture.
     * @return DialogBox object.
     */
    public static DialogBox getDukeDialog(Label label, Image image) {
        var db = new DialogBox(label, image);
        db.flip();
        return db;
    }
}

