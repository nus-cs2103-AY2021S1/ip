import duke.Duke;
import duke.GraphicalUi;
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

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/clown.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/duck.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duke.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
        if (!duke.isInProgram()) {
            System.exit(0);
        }
    }

    /**
     * Creates a dialog box which contains Duke's Greeting Message, then appends it to
     * the dialog container.
     */
    public void greet() {
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(((GraphicalUi) duke.getUi()).getGreetingMessage(),
                dukeImage));
    }

    /**
     * Creates a dialog box which contains the error message and user image, then appends it to
     * the dialog container.
     *
     * @param errorMessage Error message.
     */
    public void showLoadError(String errorMessage) {
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(errorMessage, userImage));
    }
}
