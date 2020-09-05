package nekochan.ui;

import java.io.IOException;

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

        dialog.setText(removeNewline(text));
        displayPicture.setImage(img);

        // Apply circle mask to display pictures.
        Circle mask = new Circle(displayPicture.getFitHeight() / 2);
        mask.setCenterX(displayPicture.getFitHeight() / 2);
        mask.setCenterY(displayPicture.getFitWidth() / 2);
        displayPicture.setClip(mask);
    }


    public static DialogBox getUserDialog(String text, Image image) {
        return new DialogBox(text, image).flip();
    }

    public static DialogBox getNekoDialog(String text, Image image) {
        return new DialogBox(text, image);
    }

    private DialogBox flip() {
        this.setAlignment(Pos.CENTER_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
        return this;
    }

    private String removeNewline(String content) {
        if (content.endsWith("\n")) {
            return content.substring(0, content.length() - 1);
        } else {
            return content;
        }
    }
}
