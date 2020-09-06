package duke.ui;

import duke.Duke;
import duke.command.CommandResult;
import duke.exceptions.DukeException;
import duke.messages.Output;

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

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/Kenny.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * Starts the chat bot app.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(new Output().printWelcome(), dukeImage)
        );
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
        String response;
        CommandResult commandResult = null;
        try {
            commandResult = duke.execute(userInput.getText());
            response = commandResult.getFeedback();
        } catch (DukeException e) {
            response = e.toString();
        }

        assert response != null : "No response available!";

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();

        if (commandResult == null) {
            return;
        }
        if (commandResult.isExit()) {
            Platform.runLater(() -> {
                try {
                    //Show the bye message for 1.5seconds before exit.
                    Thread.sleep(1500);
                    Platform.exit();
                } catch (InterruptedException e) {
                    dialogContainer.getChildren().addAll(
                            DialogBox.getUserDialog(e.getMessage(), dukeImage)
                    );
                }
            });
        }
    }

}
