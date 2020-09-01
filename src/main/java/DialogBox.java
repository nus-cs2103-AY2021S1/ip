import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
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

        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(text, displayPicture);
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

        DialogBox dialogBox = new DialogBox(l, iv);

        dialogBox.setBackground(new Background(new BackgroundFill(Color.AZURE,
                new CornerRadii(10),
                new Insets(0))));

        // Spacing between Image and Text
        dialogBox.setSpacing(10.0);

        // Border for User Dialog
        dialogBox.setStyle("-fx-padding: 10;" + "-fx-border-style: solid inside;"
                + "-fx-border-width: 2;" + "-fx-border-insets: 0;"
                + "-fx-border-radius: 10;" + "-fx-border-color: skyblue;");
        return dialogBox;
    }

    public static DialogBox getDukeDialog(Label l, ImageView iv) {
        var db = new DialogBox(l, iv);
        db.flip();

        db.setBackground(new Background(new BackgroundFill(Color.HONEYDEW,
                new CornerRadii(10), new Insets(0))));

        // Spacing between Image and Text
        db.setSpacing(10.0);

        // Border for Duke Dialog
        db.setStyle("-fx-padding: 10;" + "-fx-border-style: solid inside;"
                + "-fx-border-width: 2;" + "-fx-border-insets: 0;"
                + "-fx-border-radius: 10;" + "-fx-border-color: darkseagreen;");
        return db;
    }
}