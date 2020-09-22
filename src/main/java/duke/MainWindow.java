package duke;

import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
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

    private Duke duke;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/Duke.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Adds Duke's greeting message into the dialog container.
     */
    public void sendGreeting() {
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(this.duke.getGreeting(), dukeImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        ArrayList<String> responses = duke.getResponses(input);

        if (input.equals("bye")) { (
                (Stage) scrollPane.getScene().getWindow()).close();
        }
        dialogContainer.getChildren().add(
                DialogBox.getUserDialog(input, userImage)
        );
        for (String response : responses) {
            dialogContainer.getChildren().add(
                    DialogBox.getDukeDialog(response, dukeImage)
            );
        }
        userInput.clear();
    }
}
