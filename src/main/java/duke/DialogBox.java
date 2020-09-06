package duke;

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
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Paint;

public class DialogBox extends HBox {

    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;


    private DialogBox(Label l) {
        dialog = l;
        dialog.setWrapText(true);
        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(dialog);
    }

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

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> child = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(child);
        this.getChildren().setAll(child);
    }

    public static DialogBox getUserDialog(Label l) {
        DialogBox dialogBox = new DialogBox(l);
        dialogBox.setBackground(new Background(new BackgroundFill(Paint.valueOf("#ffffff"), null, null)));
        dialogBox.setBorder(new Border(new BorderStroke(Paint.valueOf("pink"),
                null, null, BorderWidths.DEFAULT)));
        return dialogBox;
    }

    public static DialogBox getDukeDialog(Label l, ImageView iv) {
        DialogBox dialogBox = new DialogBox(l.getText(), iv.getImage());
        dialogBox.flip();
        return dialogBox;
    }
}
