import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class DialogBox extends HBox {

    private Label label;
    private ImageView displayPicture;

    public DialogBox(Label lb, ImageView imageview) {
        label = lb;
        displayPicture = imageview;

        label.setWrapText(true);
        displayPicture.setFitWidth(100.0);
        displayPicture.setFitHeight(100.0);

        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(label, displayPicture);
    }
}
