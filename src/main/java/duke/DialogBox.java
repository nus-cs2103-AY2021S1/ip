package duke;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class DialogBox extends HBox {
    // DialogBox are those things that display someone's message
    // Creating custom control of Dialog box, instead of using VBox
    private Label userText;
    private ImageView profilePic;

    public DialogBox(Label l, ImageView iv) {
        this.userText = l;
        this.profilePic = iv;

        // Customized configurations
        userText.setWrapText(true);
        profilePic.setFitHeight(100.0);
        profilePic.setFitWidth(100.0);

        this.setAlignment(Pos.TOP_RIGHT); // Method from HBox
        this.getChildren().addAll(userText, profilePic);
    }

}
