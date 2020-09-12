package controller;

import duke.Duke;
import duke.DukeResponse;
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

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Display the welcome message in the GUI
     */
    public void dukeWelcome() {
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog("Hello! I'm Duke\n What can I do for you?", dukeImage));
    }

    /**
     * Displays reminder for deadline tasks that are upcoming in 3 days to user
     */
    public void displayReminder() {
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(duke.getReminderString(), dukeImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        DukeResponse response = duke.getResponse(input);

        if (response.isError()) {
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getDukeErrorDialog(response.getString(), dukeImage)
            );
        } else {
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getDukeDialog(response.getString(), dukeImage)
            );
        }
        userInput.clear();
    }
}
