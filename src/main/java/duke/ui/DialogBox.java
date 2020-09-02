package duke.ui;


import java.io.IOException;
import java.util.Collections;

import duke.ui.controller.MainWindow;
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
        dialog.setText(text);
        displayPicture.setImage(img);
    }
    //    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    //    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));
    //    private Label text;
    //    private ImageView displayPicture;
    //    /**
    //     * Constructs DialogBox
    //     * @param l Constituent label control
    //     * @param iv Constituent image view control
    //     */
    //    public DialogBox(Label l, ImageView iv) {
    //        this.text = l;
    //        this.displayPicture = iv;
    //        text.setWrapText(true);
    //        displayPicture.setFitWidth(100.0);
    //        displayPicture.setFitHeight(100.0);
    //        this.setAlignment(Pos.TOP_RIGHT);
    //        this.getChildren().addAll(this.text, this.displayPicture);
    //    }
    //
    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        // FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
        this.setAlignment(Pos.TOP_LEFT);
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
