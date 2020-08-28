import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class DialogBox extends HBox {

    private Label text;
    private ImageView displayPicture;

    public DialogBox(Label text, ImageView displayPicture, String sender) {
        this.text = text;
        this.displayPicture = displayPicture;

        text.setWrapText(true);
        displayPicture.setFitWidth(70.0);
        displayPicture.setFitHeight(70.0);

        if(sender.equals("user")) {
            this.setAlignment(Pos.TOP_LEFT);
            this.getChildren().addAll(displayPicture, text);
        } else {
            this.setAlignment(Pos.TOP_RIGHT);
            this.getChildren().addAll(text, displayPicture);
        }

    }
}
