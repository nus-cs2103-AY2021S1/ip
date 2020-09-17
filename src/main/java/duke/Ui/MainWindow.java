package duke.ui;

import duke.main.Duke;
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
    /** Scroll pane. **/
    @FXML
    private ScrollPane scrollPane;
    /** Container for the DialogBox. **/
    @FXML
    private VBox dialogContainer;
    /** User input. **/
    @FXML
    private TextField userInput;
    /** Send button. **/
    @FXML
    private Button sendButton;
    /** Duke for this MainWindow. **/
    private Duke duke;

    /** Profile image of the user, **/
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/You.png"));
    /** Profile image of Duke. **/
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/Popii.png"));

    /**
     * Initializes the MainWindow.
     **/
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Sets Duke
     *
     * @param duke Duke that wants to be used for the this MainWindow.
     */
    public void setDuke(Duke duke) {
        this.duke = duke;
    }

    /**
     * Creates two dialog boxes, one for Duke and the other for the user.
     * The content is based on the command and the response from the Duke.
     */
    @FXML
    private void handleUserInput() {
        String[] input = userInput.getText().split(" ");
        String response = duke.getResponse(input);
        dialogContainer.getChildren().addAll(
            DialogBox.getUserDialog(userInput.getText(), userImage),
            DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
        if (duke.getStatus()) {
            duke.close();
        }
    }

    /**
     * Shows the greeting message in a DialogBox.
     */
    public void showGreetingMessage() {
        String greetingMessage = duke.getGreetingMessage();
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(greetingMessage, dukeImage));
    }
}
