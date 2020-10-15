package duke.ui;

import java.io.IOException;
import java.util.Collections;

import duke.result.Result;
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
import javafx.scene.layout.Region;
import javafx.scene.shape.Circle;

public class DialogBox extends HBox {
    private static final double CLIP_RADIUS = 30.0;
    private static final String GREEN_BORDER = " -fx-border-color: green;";
    private static final String RED_BORDER = " -fx-border-color: red";

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
            e.printStackTrace();
        }

        dialog.setText(text);
        displayPicture.setImage(img);
        clipDisplayPicture(CLIP_RADIUS);
    }

    private void clipDisplayPicture(double radius) {
        double width = displayPicture.getFitWidth() / 2;
        double height = displayPicture.getFitHeight() / 2;
        Circle clip = new Circle(width, height, radius);
        displayPicture.setClip(clip);
    }

    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    private static void setDialogBoxFormat(DialogBox db, String style) {
        String newStyle = db.dialog.getStyle() + style;
        db.dialog.setStyle(newStyle);
    }

    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    public static DialogBox getDukeDialog(Result result, Image img) {
        DialogBox db = new DialogBox(result.getMessage(), img);
        db.setMinHeight(Region.USE_PREF_SIZE);
        String colourStyle = result.isSuccessful()
                ? GREEN_BORDER
                : RED_BORDER;
        setDialogBoxFormat(db, colourStyle);
        db.flip();
        return db;
    }
}
