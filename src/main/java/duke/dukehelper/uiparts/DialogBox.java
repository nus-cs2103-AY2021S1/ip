package duke.dukehelper.uiparts;

import duke.MainWindow;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;

import java.io.IOException;
import java.util.Collections;

/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;
    @FXML
    private VBox stat;

    private void baseSetUp(Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
            this.setSpacing(10);

        } catch (IOException e) {
            e.printStackTrace();
        }
        this.getStylesheets().add(this.getClass().getClassLoader().getResource("style/dialog.css").toString());
        this.getStyleClass().add("layout");
        Circle clip = new Circle(displayPicture.getX() + 35, displayPicture.getY() + 35, 35);
        displayPicture.setImage(img);
        displayPicture.setClip(clip);
    }

    private DialogBox(String text, Image img) {
        assert (text != null && img != null) : "Input is null";
        baseSetUp(img);
        dialog.getStyleClass().add("message-right");
        dialog.setText(text);
        stat.setVisible(false);
    }
    private DialogBox(String text, Image img, Statistics chart) {
        assert (text != null && img != null) : "Input is null";
        baseSetUp(img);
        dialog.setVisible(false);
        chart.getStyleClass().add("message-left");
        stat.getChildren().add(chart);
    }


    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
        //dialog.setStyle("-fx-font: 16px \"Serif\"; -fx-text-alignment: left");
        dialog.getStyleClass().add("message-left");
    }

    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }
    public static DialogBox getStatDialog(Statistics chart, Image img) {
        DialogBox dialogBox = new DialogBox("", img, chart);
        dialogBox.flip();
        return dialogBox;
    }

    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        return db;
    }
}