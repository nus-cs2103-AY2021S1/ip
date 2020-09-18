package duke.controllers;

import duke.Repl;
import duke.messages.DukeResponse;
import duke.utils.CommandHistory;
import duke.utils.Store;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
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

    /** Image that is shown alongside the user's messages. */
    private final Image userImage =
            new Image(this.getClass().getResourceAsStream("/images/Pomeranian with Mask.png"));
    /** Image that is shown alongside the chatbot's messages. */
    private final Image dukeImage =
            new Image(this.getClass().getResourceAsStream("/images/Pomeranian with Sunglasses.png"));
    /** {@code CommandHistory} object to keep track of the user input history. */
    private final CommandHistory commandHistory = new CommandHistory(50);

    /**
     * Initialises the MainWindow.
     */
    @FXML
    public void initialize() {
        // Disable the send button when the user input is empty.
        BooleanBinding isUserInputEmpty = Bindings.isEmpty(userInput.textProperty());
        sendButton.disableProperty().bind(isUserInputEmpty);

        // Set up cycling through command history.
        userInput.setOnKeyPressed(event -> handleKeyPress(event.getCode()));

        // Display greetings.
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(Store.getResourceHandler().getString("repl.greeting"), dukeImage));

        // Scroll to bottom of scroll pane on update.
        dialogContainer.heightProperty().addListener((observable, oldValue, newValue) ->
                scrollPane.setVvalue(1.0d));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing. Responsible for closing the {@code stage} once the
     * user inputs a command to exit the chatbot.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();

        // Do nothing if user input is empty.
        if (input.isEmpty()) {
            return;
        }

        // Push user input into command history.
        commandHistory.addCommand(input);

        // Execute the inputted command.
        DukeResponse dukeResponse = Repl.getResponse(input);

        // Display the response from command execution.
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(dukeResponse.toString(), dukeImage)
        );

        // Terminate the application if the signal to exit is received.
        if (dukeResponse.shouldExit()) {
            Stage stage = (Stage) userInput.getScene().getWindow();
            stage.close();
        }

        // Clear the user input.
        userInput.clear();
    }

    /**
     * Handles key presses for `userInput`, displaying the command history on
     * {@code KeyCode.UP} and {@code KeyCode.DOWN}.
     *
     * @param keyCode the key that was pressed by the user.
     */
    private void handleKeyPress(KeyCode keyCode) {
        switch (keyCode) {
        case UP:
            userInput.setText(commandHistory.navigateUp());
            break;
        case DOWN:
            userInput.setText(commandHistory.navigateDown());
            break;
        default:
            return;
        }
        // Set the caret position to the end of the string.
        userInput.positionCaret(userInput.getLength());
    }
}
