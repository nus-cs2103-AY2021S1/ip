package duke;

import javafx.scene.text.Text;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.fxml.FXMLLoader;
import javafx.fxml.FXML;

import java.io.IOException;

/**
 * This control represents a dialog box consisting of an ImageView to represent the User's face and a label
 * containing text from the User.
 */
public class UserDialogBox extends HBox {
    @FXML
    private Text dialog;
    @FXML
    private ImageView displayPicture;

    /**
     * Constructor for UserDialogBox.
     * @param text String to be displayed in UserDialogBox
     * @param img Image to be displayed in UserDialogBox
     */
    public UserDialogBox(String text, Image img) {
        try {
            String fxmlResourcePath = "/view/UserDialogBox.fxml";
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource(fxmlResourcePath));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.dialog.setText("   " + text);
        this.displayPicture.setImage(img);
        this.setHeight(Region.USE_COMPUTED_SIZE);
    }
}