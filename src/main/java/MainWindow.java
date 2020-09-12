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

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/ironman.png"));
    private Image jarvisImage = new Image(this.getClass().getResourceAsStream("/images/jarvis.png"));

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
    private void handleUserInput() {
        String input = userInput.getText();
        this.userMessage.setText(input);
        String response = jarvis.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getJarvisDialog(response, jarvisImage)
        );
        userInput.clear();
    }
}