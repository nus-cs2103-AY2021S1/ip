package duke.ui.visualui;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

/**
 * UI design that shows Duke logo at the start
 */

public class OpeningBox extends VBox {

    @FXML
    private Label greetingMessage;
    @FXML
    private ImageView displayPicture;

    private OpeningBox(String greetingText, Image image) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/OpeningBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        setProperties(greetingText, image);
    }

    public static OpeningBox getOpeningMessage(String greetingText, Image image) {
        return new OpeningBox(greetingText, image);
    }

    private void setProperties(String greetingText, Image image) {
        greetingMessage.setText(greetingText);
        greetingMessage.getStylesheets().add("view/OpeningBox.css");
        displayPicture.setImage(image);
    }
}

