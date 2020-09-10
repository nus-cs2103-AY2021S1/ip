package duke;

import java.io.IOException;
import java.util.Collections;

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

/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class StartingBox extends HBox {
    @FXML
    private Label welcomeText;
//    @FXML
//    private ImageView startingPicture;
    @FXML
    private ImageView leftPicture;
    @FXML
    private ImageView rightPicture;

    private StartingBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/StartingBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        welcomeText.setText(text);
//        startingPicture.setImage(img);
        leftPicture.setImage(img);
        rightPicture.setImage(img);
    }


    public static StartingBox getStartMessage(String text, Image img) {

        return new StartingBox(text, img);
    }


}