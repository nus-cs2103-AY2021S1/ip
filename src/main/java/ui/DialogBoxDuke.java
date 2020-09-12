package ui;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;



public class DialogBoxDuke extends HBox {

    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    private DialogBoxDuke(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBoxDuke.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //this.setBackground(new Background(Color.RED));
        Circle clip = new Circle(50.0, 50.0, 50.0);
        dialog.setText(text);
        displayPicture.setImage(img);
        displayPicture.setClip(clip);
    }

    public static DialogBoxDuke getDukeDialog(String text, Image img) {
        DialogBoxDuke dialogBoxDuke = new DialogBoxDuke(text, img);
        if (text.contains("OOPS!!")) {
            dialogBoxDuke.dialog.setTextFill(Color.RED);
        }
        return dialogBoxDuke;
    }
}
