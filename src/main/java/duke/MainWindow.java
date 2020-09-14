package duke;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 * Represents the controller for MainWindow. Provides the
 * layout for the other controls.
 */
public class MainWindow extends AnchorPane {

    private static final double TIME_DELAY = 1;
    private static final String USER_IMAGE_PATH = "/images/user.png";
    private static final String DUKE_IMAGE_PATH = "/images/duke.png";

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Duke duke;

    private Image userImage = new Image(this.getClass().getResourceAsStream(USER_IMAGE_PATH));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream(DUKE_IMAGE_PATH));


    /**
     * Initializes the GUI window.
     */
    @FXML
    private void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Sets the Duke object. Sends the greeting message to user by
     * adding the message in the dialog box.
     *
     * @param duke The Duke object.
     */
    public void setDuke(Duke duke) {
        this.duke = duke;
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(duke.getUi().displayGreeting(), dukeImage));

    }

    /**
     * Creates a dialog boxes echoing user input, and another dialog box
     * containing Duke's reply, and then appends the two dialog boxes to
     * the dialog container. The user input is cleared after processing.
     * There is a delay of 1.5 seconds before the program close when the user
     * inputs "bye."
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
        if (duke.canCloseWindow()) {
            PauseTransition delay = new PauseTransition((Duration.seconds(TIME_DELAY)));
            delay.setOnFinished(event -> System.exit(0));
            delay.play();
        }

    }
}
