package duke.controllers;

import javafx.collections.FXCollections; // ??
import javafx.collections.ObservableList; // ??
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.Node;
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

        this.setAlignment(Pos.TOP_RIGHT); // Method from HBox. By default, text is top-right
        this.setPadding(new Insets(10.0, 10.0, 10.0, 10.0)); // pad from one DB to another
        this.setSpacing(10.0); // spacing between children eg: text and image
        this.setBackground(new Background(new BackgroundFill(Color.ALICEBLUE, new CornerRadii(20.0), new Insets(5.0, 0.0, 5.0, 0.0)))); // time to create some background value
        this.getChildren().addAll(userText, profilePic);
    }

    // more customisation functions

    /**
     * Flips the entire dialog box. Imageview on the left, text on the right
     * 1 Set alignment of this DialogBox to the left
     * 2 Create a list of nodes and reverse that list, to switch all nodes involved.
     * 3 set children for dialogBox with reversed list => All done
     */
    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> temp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(temp);
        this.getChildren().setAll(temp);
    }

    public static DialogBox getUserDialogBox(Label l, ImageView iv) {
        return new DialogBox(l, iv); // Not flipped
    }

    public static DialogBox getDukeDialogBox(Label l, ImageView iv) {
        DialogBox db = new DialogBox(l, iv);
        db.flip();
        return db;
    }
}
