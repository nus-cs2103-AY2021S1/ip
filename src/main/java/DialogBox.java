import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class DialogBox extends HBox {

    private Label text;
    private ImageView displayPicture;

    public DialogBox(Label l, ImageView iv) {
        text = l;
        displayPicture = iv;

        text.setWrapText(true);

        displayPicture.setFitWidth(100.0);
        displayPicture.setFitHeight(100.0);
        Circle circle = new Circle(displayPicture.getLayoutX() + 50,
                displayPicture.getLayoutY() + 50, 50);
        displayPicture.setClip(circle);

        this.setAlignment(Pos.CENTER_RIGHT);
        this.getChildren().addAll(text, displayPicture);
        this.setPadding(new Insets(0, 5, 5, 5));
        this.setSpacing(10);
        this.setBorder(new Border(new BorderStroke(Color.TRANSPARENT, Color.TRANSPARENT, Color.BLACK, Color.TRANSPARENT,
                BorderStrokeStyle.NONE, BorderStrokeStyle.NONE, BorderStrokeStyle.DASHED, BorderStrokeStyle.NONE,
                CornerRadii.EMPTY, new BorderWidths(1), new Insets(0, 10, 0, 10))));
        this.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        this.setAlignment(Pos.CENTER_LEFT);
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
}
