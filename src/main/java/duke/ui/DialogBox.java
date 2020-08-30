package duke.ui;

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
import javafx.scene.shape.Circle;


public class DialogBox extends HBox {
    private static final Insets BOX_PADDING = new Insets(10, 2 , 10 , 2);
    private static final Insets TEXT_PIC_PADDING = new Insets(0, 0, 0 , 10);

    @FXML
    private Label dialog = new Label();
    @FXML
    private ImageView displayPicture = new ImageView();

    /**
     * Creates a dialog box in GUI.
     *
     * @param l Label to be displayed.
     * @param iv Imageview to be displayed
     */
    private DialogBox(Label l, ImageView iv) {
        Circle clipCircle = new Circle(50, 50, 50);

        l.setWrapText(true);
        l.setPadding(DialogBox.TEXT_PIC_PADDING);
        iv.setFitWidth(100.0);
        iv.setFitHeight(100.0);
        iv.setClip(clipCircle);

        this.setPadding(DialogBox.BOX_PADDING);
        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(l, iv);
    }

    private DialogBox(String text, Image img) {
        Circle clipCircle = new Circle(50, 50, 50);
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
        displayPicture.setClip(clipCircle);
    }

    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    public static DialogBox getUserDialog(Label l, ImageView iv) {
        return new DialogBox(l, iv);
    }

    public static DialogBox getDukeDialog(Label l, ImageView iv) {
        DialogBox db = new DialogBox(l, iv);
        db.flip();
        return db;
    }

    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        return db;
    }
}
