package duke.ui;

import java.io.IOException;
import java.util.List;

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
import javafx.scene.shape.Circle;


public class DialogBox extends HBox {
    @FXML
    private final HBox dialogContainer = new HBox();
    @FXML
    private ImageView displayPicture = new ImageView();
    @FXML
    private Label dialog = new Label();

    /**
     * Creates a dialog box in GUI.
     *
     * @param text Text to be displayed.
     * @param img Image to be displayed
     */
    private DialogBox(String text, Image img) {
        Circle clipCircle = new Circle(40, 40, 40);

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


    public static DialogBox getUserDialog(String text, Image img) {
        var db = new DialogBox(text,img);

        Node target = findNodeById("textbox",db.getChildren());

        assert target != null;
        target.setStyle("-fx-background-color: #E4D7F9");

        return db;
    }

    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img);

        db.flip();

        Node target = findNodeById("textbox",db.getChildren());

        assert target != null;
        target.setStyle("-fx-background-color: #9156F0");

        return db;
    }

    private static Node findNodeById(String id, List<Node> childrenList) {
        for (Node subview: childrenList) {
            if (subview.getId().equals(id)) {
                return subview;
            }
        }

        assert false;
        return null;
    }
}
