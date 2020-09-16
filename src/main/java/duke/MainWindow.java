package duke;

import java.io.IOException;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
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

    /**
     * Initialise main windows.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        addDukeDialog(StringConstants.getGreeting());
    }

    /**
     * Set duke object to main window.
     * @param d duke object
     */
    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     *  Handles user input by getting duke response, adding dialog boxes and clearing user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response;
        UserCommandType userCommandType;

        try {
            userCommandType = Parser.parseUserCommand(input);
            response = duke.getResponse(input, userCommandType);
        } catch (Parser.InvalidCommandException | TaskList.InvalidIndexException | IOException exception) {
            response = exception.getMessage();
            userCommandType = UserCommandType.ERROR;
        }

        addUserAndDukeDialogs(input, response);
        userInput.clear();
        if (userCommandType == UserCommandType.EXIT) {
            Platform.exit();
        }
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container.
     * @param userDialog user dialog String
     * @param dukeDialog Duke dialog String
     */
    private void addUserAndDukeDialogs(String userDialog, String dukeDialog) {
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userDialog),
                DialogBox.getDukeDialog(dukeDialog)
        );
    }

    /**
     * Creates Duke dialog box and appends to dialog container.
     * @param dukeDialog Duke dialog String
     */
    private void addDukeDialog(String dukeDialog) {
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(dukeDialog));
    }
}


