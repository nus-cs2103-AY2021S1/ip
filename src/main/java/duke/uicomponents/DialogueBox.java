package duke.uicomponents;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;


/**
 * A single component for the rendering of the dialogue box in a chat dialogue.
 */
public class DialogueBox extends HBox {

    private Label text;
    private ImageView displayPicture;


    /**
     * Constructor for a dialogue box in an opened chat.
     */
    public DialogueBox(Label text, ImageView displayPicture) {
        this.text = text;
        this.displayPicture = displayPicture;

        text.setWrapText(true);
        displayPicture.setFitHeight(100.0);
        displayPicture.setFitWidth(100.0);

        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(text, displayPicture);
    }
}
