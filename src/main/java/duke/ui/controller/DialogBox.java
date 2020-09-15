package duke.ui.controller;

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
import javafx.scene.text.Font;

/**
 * Encapsulation of a dialog box that consists of two
 * different components, an ImageView and Label
 */
public class DialogBox extends HBox {

    // instance variables
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource(
                    "/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        dialog.setMinHeight(Region.USE_PREF_SIZE);
        dialog.setMinWidth(Region.USE_PREF_SIZE);
        displayPicture.setImage(img);
    }

    private void flip() {
        ObservableList<Node> temp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(temp);
        getChildren().setAll(temp);
        setAlignment(Pos.CENTER_LEFT);
    }

    public static DialogBox getUserDialog(String text, Image img) {
        var box = new DialogBox(text, img);
        BackgroundFill fill = new BackgroundFill(
                Color.web("#29b6f6"),
                new CornerRadii(5.0),
                Insets.EMPTY
        );
        Background background = new Background(fill);
        box.dialog.setBackground(background);
        box.dialog.setFont(Font.font("Times New Roman"));
        return box;
    }

    public static DialogBox getDukeDialog(String text, Image img) {
        var box = new DialogBox(text, img);
        box.flip();
        BackgroundFill fill = new BackgroundFill(
                Color.web("#64ba69"),
                new CornerRadii(5.0),
                Insets.EMPTY
        );
        Background background = new Background(fill);
        box.dialog.setBackground(background);
        return box;
    }
}
