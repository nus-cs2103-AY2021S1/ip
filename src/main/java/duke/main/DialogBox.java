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
import javafx.scene.layout.CornerRadii;
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
     * @param text Text to be inserted in the label.
     * @param displayPicture Image to be inserted in the image view.
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

    private Background setBackground(Color color, CornerRadii radius) {
        return new Background(new BackgroundFill(color, radius, null));
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
        CornerRadii corn = new CornerRadii(10);
        Color backgroundColor = Color.rgb(69, 238, 82);
        Background background = db.setBackground(backgroundColor, corn);
        db.dialog.setBackground(background);
        assert !db.getText().equals("") : "User input cannot be empty";
        return db;
    }

    public static DialogBox getDukeDialog(String text, Image image) {
        DialogBox dukeBox = new DialogBox(text, image);
        dukeBox.flip();
        CornerRadii corn = new CornerRadii(10);
        Color backgroundColor = Color.rgb(53, 179, 255);
        Background background = dukeBox.setBackground(backgroundColor, corn);
        dukeBox.dialog.setBackground(background);
        assert !dukeBox.getText().equals("") : "Duke's response cannot be empty";
        return dukeBox;
    }
}
