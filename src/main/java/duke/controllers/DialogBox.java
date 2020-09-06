package duke.controllers;

import java.io.IOException;

import javafx.collections.FXCollections; // ??
import javafx.collections.ObservableList; // ??
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
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
    @FXML
    private Label userText;
    @FXML
    private ImageView profilePic;

    public DialogBox(String text, Image img) {
        // Iteration 2: Adding this as FXML Controller
        this.userText = new Label();
        this.profilePic = new ImageView();

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.userText.setText(text);
        this.profilePic.setImage(img);
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

    public static DialogBox getUserDialogBox(String txt, Image img) {
        return new DialogBox(txt, img); // Not flipped
    }

    public static DialogBox getDukeDialogBox(String txt, Image img) {
        DialogBox db = new DialogBox(txt, img);
        db.flip();
        return db;
    }
}
