import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

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

    private Duke duke;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/megantheestallion.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/cardib.png"));

    /**
     * Initializes the dialogbox
     */
    @FXML
    public void initialize() {
        assert(userImage != null);
        assert(dukeImage != null);
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        printToUser(Ui.printWelcome());
    }

    public void setDuke(Duke duke) {
        this.duke = duke;
    }

    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duke.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        if (input.equals("bye")) {
            closeStage();
        }
        userInput.clear();
    }

    @FXML
    private void printToUser(String message) {
        assert(!message.isEmpty());
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(message, dukeImage)
        );
    }

    /**
     * Closes the window when the exit condition is input
     */
    @FXML
    void closeStage() {
        Stage stage = (Stage) userInput.getScene().getWindow();
        stage.close();
    }
}
