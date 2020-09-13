package duke.ui;

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
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;


public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;
    @FXML
    private BackgroundFill backgroundColor;

    private DialogBox(String text, Image img, BackgroundFill backgroundColor) {
        assert text != null;
        assert img != null;
        assert backgroundColor != null;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(
                MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        dialog.setText(text);
        dialog.setMinHeight(Region.USE_PREF_SIZE);
        displayPicture.setImage(img);
        this.setBackground(
                new Background(backgroundColor));
    }

    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
    }

    public static DialogBox getUserDialog(String text, Image iv) {
        assert text != null;
        assert iv != null;
        return new DialogBox(text, iv,
                new BackgroundFill(Color.LIGHTBLUE, new CornerRadii(20), new Insets(5, 5, 5, 5)));
    }

    public static DialogBox getDukeDialog(String text, Image iv) {
        assert text != null;
        assert iv != null;
        var db = new DialogBox(text, iv,
                new BackgroundFill(Color.PINK, new CornerRadii(20), new Insets(5, 3, 5, 5)));
        db.flip();
        return db;
    }

    public static DialogBox getDukeErrorDialog(String text, Image iv) {
        assert text != null;
        assert iv != null;
        var db = new DialogBox(text, iv,
                new BackgroundFill(Color.INDIANRED, new CornerRadii(20), new Insets(5, 3, 5, 5)));
        db.flip();
        return db;
    }
}
