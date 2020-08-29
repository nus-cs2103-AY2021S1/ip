package duke;


import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class DialogBox extends HBox {
    private Label text;
    private ImageView displayPicture;

    /**
     * Constructor for the class.
     * @param label the text to be displayed
     * @param displayPicture the picture to be displayed
     */
    public DialogBox(Label label, ImageView displayPicture) {
        this.text = label;
        this.displayPicture = displayPicture;

        text.setWrapText(true);
        displayPicture.setFitWidth(100.0);
        displayPicture.setFitHeight(100.0);

        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(text, displayPicture);
    }

}
