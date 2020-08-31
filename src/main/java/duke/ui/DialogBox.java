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
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;


public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;
    @FXML
    private BackgroundFill backgroundColor;

    private DialogBox(double spacing, String text, Image img, BackgroundFill backgroundColor, Circle circle) {
        super(spacing);
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        dialog.setText(text);
        displayPicture.setImage(img);
        displayPicture.setClip(circle);
        this.setBackground(new Background(backgroundColor));
    }

    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
    }

    public static DialogBox getUserDialog(String text, Image iv) {
        return new DialogBox(10, text, iv,
                new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY),
                new Circle(48, 43, 45));
    }

    public static DialogBox getDukeDialog(String text, Image iv) {
        var db = new DialogBox(10, text, iv,
                new BackgroundFill(Color.PINK, CornerRadii.EMPTY, Insets.EMPTY),
                new Circle(45, 45, 45));
        db.flip();
        return db;
    }
}
