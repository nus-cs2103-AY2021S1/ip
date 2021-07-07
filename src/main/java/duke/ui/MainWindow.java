package duke.ui;

import duke.Duke;

import duke.commands.CommandOutput;

import duke.utils.Messages;
import javafx.application.Platform;
import javafx.beans.binding.BooleanBinding;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

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

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * Initialises the main window of the application with its appropriate properties.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        scrollPane.setFitToWidth(true);
        BooleanBinding isUserInputEmpty = new BooleanBinding() {
            {
                super.bind(userInput.textProperty());
            }
            @Override
            protected boolean computeValue() {
                return userInput.getText().isEmpty();
            }
        };
        sendButton.disableProperty().bind(isUserInputEmpty);
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(Messages.WELCOME_MESSAGE, dukeImage)
        );
    }

    public void setDuke(Duke d) {
        duke = d;
    }

    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        if (input.isEmpty()) {
            return;
        }

        CommandOutput executionOutput = duke.getCommandExecutionOutput(input);
        String response = executionOutput.getCommandOutput();
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
        if (executionOutput.isExit()) {
            Platform.exit();
            System.exit(0);
        }
    }
}
