import duke.ui.UI;
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
    /**
     * Scroll pane for conservations.
     */
    @FXML
    private ScrollPane scrollPane;
    /**
     * Dialog container for conversations.
     */
    @FXML
    private VBox dialogContainer;
    /**
     * User's input.
     */
    @FXML
    private TextField userInput;
    /**
     * Send button for user.
     */
    @FXML
    private Button sendButton;
    /**
     * Duke.
     */
    private Duke duke;
    /**
     * Number of times user has input their name.
     */
    private int numberOfTimes;
    /**
     * Gets the image for User.
     */
    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    /**
     * Gets the image for Duke.
     */
    private final Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * Initialises the main window.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(UI.greetUser(), dukeImage)
        );
    }

    /**
     * Sets Duke.
     * @param duke Duke.
     */
    public void setDuke(Duke duke) {
        this.duke = duke;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply,
     * and appends them to the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        if (numberOfTimes == 0) {
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getDukeDialog(UI.addressUser(input), dukeImage)
            );
            numberOfTimes++;
        } else {
            String response = duke.getResponse(input);
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getDukeDialog(response, dukeImage)
            );
            if (input.equals("bye")) {
                Platform.exit();
            }
        }
        userInput.clear();
    }
}
