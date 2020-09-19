package duke.ui;

import static duke.utils.Messages.MESSAGE_GREETING;

import duke.Duke;
import duke.commands.CommandResult;
import duke.exceptions.DukeException;
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

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/chatbot.png"));

    /**
     * Initialises the main window with a greeting message.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(MESSAGE_GREETING, dukeImage)
        );
        userInput.textProperty().addListener(observable -> refreshPromptVisibility());
    }

    private void refreshPromptVisibility() {
        final String text = userInput.getText();
        if (isEmptyString(text)) {
            userInput.getStyleClass().remove("no-prompt");
            sendButton.setDisable(true);
            userInput.setOnAction(null);
        } else {
            if (!getStyleClass().contains("no-prompt")) {
                getStyleClass().add("no-prompt");
            }
            sendButton.setDisable(false);
            userInput.setOnAction(event -> handleUserInput());
        }
    }

    private boolean isEmptyString(String text) {
        return text == null || text.isEmpty();
    }

    public void setDuke(Duke d) {
        this.duke = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response;
        try {
            CommandResult result = duke.getResult(input);
            if (result.isExit()) {
                Platform.exit();
                System.exit(0);
                return;
            }
            response = result.getFeedbackToUser();
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getDukeDialog(response, dukeImage)
            );
        } catch (DukeException e) {
            response = e.getMessage();
            DialogBox dukeDialog = DialogBox.getDukeDialog(response, dukeImage);
            dukeDialog.getDialog().setStyle("-fx-effect: dropshadow(gaussian, red, 10, 0, 0, 1)");
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    dukeDialog
            );
        }

        userInput.clear();
    }
}
