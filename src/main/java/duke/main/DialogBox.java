package duke.main;

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
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;


public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    /**
     * Initializes a DialogBox object.
     *
     * @param text Text to be inserted in the dialog box.
     * @param displayPicture Image to be inserted in the dialog box.
     */
    public DialogBox(String text, Image displayPicture) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.dialog.setText(text);
        this.dialog.setPadding(new Insets(0, 10, 0, 10));
        this.displayPicture.setImage(displayPicture);
    }

    private void setBackgroundColor(Color color) {
        this.setBackground(new Background(new BackgroundFill(color, null, null)));
    }

    private String getText() {
        return this.dialog.getText();
    }
    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    public static DialogBox getUserDialog(String text, Image image) {
        DialogBox db = new DialogBox(text, image);
        db.setBackgroundColor(Color.GREEN);
        assert !db.getText().equals("") : "User input cannot be empty";
        return db;
    }

    public static DialogBox getDukeDialog(String text, Image image) {
        DialogBox dukeBox = new DialogBox(text, image);
        dukeBox.flip();
        dukeBox.setBackgroundColor(Color.YELLOW);
        assert !dukeBox.getText().equals("") : "Duke's response cannot be empty";
        return dukeBox;
    }
}
