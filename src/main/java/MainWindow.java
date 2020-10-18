import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;
    @FXML
    private ImageView userPicture;
    @FXML
    private Label userMessage;

    private Jarvis jarvis;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/ironman.png"));
    private final Image jarvisImage = new Image(this.getClass().getResourceAsStream("/images/jarvis.png"));

    /**
     * Initializes the scroll pane and sets the user image
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        this.userPicture.setImage(userImage);
    }

    public void setJarvis(Jarvis d) {
        jarvis = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Jarvis's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() throws IOException {
        String input = userInput.getText();
        this.userMessage.setText(input);
        Command response = jarvis.getResponse(input);
        String jarvisMsg = response.execute();
        dialogContainer.getChildren().addAll(
                DialogBox.getJarvisDialog(jarvisMsg, jarvisImage)
        );
        userInput.clear();
        if (response.isExit()) {
            System.exit(0);
        }
    }
}
