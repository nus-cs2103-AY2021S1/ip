package duke.gui;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    /**
     * Creates a new DialogBox object which displays some text and an image side by side.
     * @param text Text to be displayed.
     * @param im Image to be displayed.
     */
    public DialogBox(String text, Image im) {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/view/DialogBox.fxml"));
            loader.setController(this);
            loader.setRoot(this);
            loader.load();
            VBox.setMargin(this, new Insets(10));
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        displayPicture.setImage(im);
    }

    public static DialogBox getUserDialog(String text, Image im) {
        return new DialogBox(text, im);
    }

    public static DialogBox getDukeDialog(String text, Image im) {
        DialogBox reversedOrientation = new DialogBox(text, im);
        reversedOrientation.flip();
        return reversedOrientation;
    }

    private void flip() {
        ObservableList<Node> children = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(children);
        getChildren().setAll(children);
        setAlignment(Pos.TOP_LEFT);
        setStyle("-fx-background-color: #344955;");
    }

}
