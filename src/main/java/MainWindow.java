import exceptions.DukeException;
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

    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DarkKermit.PNG"));
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/Kermit.PNG"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke(Duke d) {
        duke = d;
        greetUser();
    }

    /**
     * Adds greeting to dialog box.
     */
    public void greetUser() {
        String response = duke.ui.greetings();
        duke.setHasGreeted();
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(response, dukeImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = null;
        try {
            response = duke.getResponse(input);
        } catch (DukeException e) {
            e.printStackTrace();
        }
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );

        if (userInput.getText().equals("bye")) {
            dialogContainer.getChildren().addAll(
                    DialogBox.getDukeDialog(duke.ui.goodbye(), dukeImage)
            );
            System.exit(0); // Closes GUI window.
        }

        userInput.clear();
    }
}
