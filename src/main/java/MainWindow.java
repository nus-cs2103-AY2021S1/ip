import duke.Duke;
import duke.Response;
import duke.exceptions.DukeException;
import duke.exceptions.DukeStorageException;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
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

    private Duke duke;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image botImage = new Image(this.getClass().getResourceAsStream("/images/Bot.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void start() {
        try {
            duke = new Duke(true);
        } catch (DukeStorageException e) {
            print(e.getMessage());
            duke = new Duke(false);
        } finally {
            greet();
        }
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing duke.Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        printInput(input);
        try {
            Response response = duke.getResponse(input);
            print(response.getResponseMessage());
            if (response.shouldExit()) {
                Platform.exit();
            }
        } catch (DukeException e) {
            print(e.getMessage());
        } finally {
            userInput.clear();
        }
    }

    private void printInput(String input) {
        dialogContainer.getChildren().add(
                DialogBox.getUserDialog(input, userImage)
        );
    }

    private void print(String response) {
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(response, botImage)
        );
    }

    private void greet() {
        String welcomeMessage = "Konnichiwa!\n"
                + "What can I do for you?\n";;
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(welcomeMessage, botImage));
    }
}
