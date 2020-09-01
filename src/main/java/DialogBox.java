import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class DialogBox extends HBox{
    private Label text;
    private ImageView displayPicture;
    private final double LENGTH = 50.0;

    public DialogBox(Label l, ImageView iv) {
        text = l;
        text.setTextFill(Color.MIDNIGHTBLUE);

        displayPicture = iv;

        text.setWrapText(true);
        displayPicture.setFitWidth(LENGTH);
        displayPicture.setFitHeight(LENGTH);

        displayPicture = circleClip(displayPicture);

        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(text, displayPicture);
        this.setSpacing(15);
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

    public static DialogBox getUserDialog(Label l, ImageView iv) {
        return new DialogBox(l, iv);
    }

    public static DialogBox getDukeDialog(Label l, ImageView iv) {
        var db = new DialogBox(l, iv);
        db.flip();
        return db;
    }

    private ImageView circleClip(ImageView imageView){
        Circle circle = new Circle(LENGTH /2);
        imageView.setClip(circle);
        circle.setCenterY(imageView.getY() + LENGTH/2);
        circle.setCenterX(imageView.getX() + LENGTH/2);
        return imageView;
    }
}


