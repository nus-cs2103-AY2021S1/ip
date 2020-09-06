import java.io.File;
import java.io.IOException;

import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.shape.Circle;

public class DialogBox extends HBox {

    @FXML
    private Label label;
    @FXML
    private ImageView displayPicture;

    private final static Image USER_IMAGE =
            new Image(new File("src/main/resources/images/user.png").toURI().toString());
    private final static Image PEANUT_IMAGE =
            new Image(new File("src/main/resources/images/peanut.png").toURI().toString());


    protected DialogBox(String text, Image img, Color backgroundColor) {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        label.setText(text);
        displayPicture.setImage(img);
        displayPicture.setClip(new Circle(50, 50, 50));
        this.setBackground(new Background(new BackgroundFill(backgroundColor,null,null)));

    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    protected static DialogBox getUserDialog(String text) {
        DialogBox db = new DialogBox(text, USER_IMAGE, Color.NAVAJOWHITE);
        db.flip();
        return db;
    }

    protected static DialogBox getDukeDialog(String text) {
        return new DialogBox(text, PEANUT_IMAGE, Color.DODGERBLUE);
    }
}