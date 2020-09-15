package duke.gui;

import java.io.IOException;
import java.util.Collections;

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

    private static boolean isUserDialog = false;
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        setPadding(new Insets(10));
        dialog.setPadding(new Insets(10));
        dialog.setText(text);
        displayPicture.setImage(img);
        setBackground(new Background(new BackgroundFill(isUserDialog ? Color.LIGHTBLUE : Color.LIGHTGREY,
                new CornerRadii(5), new Insets(5))));
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.CENTER_LEFT);
    }

    /** Gets the user dialog.
     *
     * @param text The dialog message.
     * @param img The associated image.
     * @return The user dialog.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        isUserDialog = true;
        return new DialogBox(text, img);
    }

    /** Gets Duke's dialog.
     *
     * @param text The dialog message.
     * @param img The associated image.
     * @return Duke's dialog.
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        isUserDialog = false;
        var db = new DialogBox(text, img);
        db.flip();
        return db;
    }
}
