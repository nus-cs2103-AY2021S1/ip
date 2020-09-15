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
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Duke duke = new Duke();

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/SpeechEmoji.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/GeekEmoji.png"));

    /**
     * Initialises the MainWindow. Sends a welcome message with the list of tasks.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());

        String helloMessage = UI.getHelloMessage() + "\n\nI've saved these so far:\n"
                + duke.getResponse("list");
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(helloMessage, dukeImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        if (!input.equals("")) {
            String response = duke.getResponse(input);
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getDukeDialog(response, dukeImage)
            );
            userInput.clear();

            if (duke.hasExited()) {
                userInput.setDisable(true);
                sendButton.setDisable(true);
            }
        }
    }
}
