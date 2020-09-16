package duke.ui;

import duke.Duke;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

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

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/sushi.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/onigiri.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Sets the Duke object that will be processing all the user commands and displays greetings by the "Bob".
     *
     * @param d Duke object.
     */
    public void setDuke(Duke d) {
        duke = d;
        displayGreetings();
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

        if (duke.canExitProgram()) {
            // Delay closing of window
            PauseTransition delay = new PauseTransition(Duration.seconds(1));
            delay.setOnFinished(event -> Platform.exit());
            delay.play();
        }
    }

    /**
     * Creates a dialog box containing the Duke's greeting when the program first starts up. Duke's greeting
     * includes displaying the currently saved task list in the hard drive and a standard greeting message.
     */
    private void displayGreetings() {
        String greetings = duke.getGreetings();
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(greetings, dukeImage));
    }
}
