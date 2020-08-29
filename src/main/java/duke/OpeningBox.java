package duke;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class OpeningBox extends VBox {
    @FXML
    private Label openingMessage;
    @FXML
    private Label greetingMessage;
    @FXML
    private ImageView displayPicture;

    private OpeningBox(String openingText, String greetingText, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/OpeningBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        openingMessage.setText(openingText);
        greetingMessage.setText(greetingText);
        displayPicture.setImage(img);
    }
    
    public static OpeningBox getOpeningMessage(String openingText, String greetingText, Image img) {
        return new OpeningBox(openingText, greetingText, img);
    }
}
