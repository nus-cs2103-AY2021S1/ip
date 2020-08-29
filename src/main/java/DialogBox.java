import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;

public class DialogBox extends HBox {

    public DialogBox(Label l, ImageView iv) {
        l.setFont(Font.font ("Verdana", 11));
        l.setTextFill(Color.web("#596186"));
        Circle clip = new Circle(50,50,45);
        iv.setClip(clip);

        l.setWrapText(true);
        iv.setFitWidth(100.0);
        iv.setFitHeight(100.0);

        l.setAlignment(Pos.CENTER);
        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(l, iv);
    }

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
}
