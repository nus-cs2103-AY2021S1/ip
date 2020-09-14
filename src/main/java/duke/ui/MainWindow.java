package duke.ui;

import duke.Duke;
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
    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private final Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/duke.png"));
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;
    private Duke duke;

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Sends welcome message to the client.
     */
    public void sendWelcomeMessage() {
        String response = duke.getWelcome();
        dialogContainer.getChildren().addAll(
                DukeDialogBox.getDukeDialog(response, dukeImage)
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
        String response = duke.getResponse(input);

        if (duke.isError()) {
            dialogContainer.getChildren().addAll(
                    UserDialogBox.getUserDialog(input, userImage),
                    ErrorDialogBox.getErrorDialog(response, dukeImage)
            );
        } else {
            dialogContainer.getChildren().addAll(
                    UserDialogBox.getUserDialog(input, userImage),
                    DukeDialogBox.getDukeDialog(response, dukeImage)
            );
        }

        if (duke.isExit()) {
            System.exit(0);
        }

        userInput.clear();
    }

}
