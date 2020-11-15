package duke.ui;

import duke.Duke;
import duke.exception.DukeException;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

// @@author aizatazhar-reused
// Reused from https://se-education.org/guides/tutorials/javaFxPart4.html with minor modifications
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

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.jpg"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/duke.png"));

    /**
     * Initialises the main window with Duke's greeting.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(new Ui().greet(), dukeImage, false)
        );
    }

    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply
     */
    @FXML
     private void handleUserInput() {
        String input = userInput.getText();
        String response;
        boolean isErrorMessage;
        try {
            response = duke.getResponse(input);
            isErrorMessage = false;
        } catch (DukeException e) {
            response = e.getMessage();
            isErrorMessage = true;
        }

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage, isErrorMessage)
        );

        if (response.equals("Bye. Hope to see you again soon!")) {
            Platform.exit();
        }

        userInput.clear();
    }
}
// @@author
