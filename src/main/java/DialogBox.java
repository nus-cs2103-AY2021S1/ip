import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class DialogBox extends HBox {

    private Label text;

    public DialogBox(Label l) {
        text = l;
        text.setStyle("-fx-text-fill: #D0D0D0; -fx-font-family:\"consolas\"; -fx-font-size:14px; -fx-font-weight:bold;");
        text.setPadding(new Insets(5, 0,5, 10));
        this.setAlignment(Pos.TOP_LEFT);
        this.getChildren().addAll(text);
    }

    public static DialogBox getUserDialog(Label l) {
        return new DialogBox(l);
    }

    public static DialogBox getDukeDialog(Label l) {
        return new DialogBox(l);
    }
}
