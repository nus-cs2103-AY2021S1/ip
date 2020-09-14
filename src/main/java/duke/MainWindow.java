package duke;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for duke.MainWindow. Provides the layout for the other controls.
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

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaDoge.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaMusk.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Show introduction and help message when
     * GUI is first initialized.
     */
    public void showIntroduction() {
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(duke.getGreeting(), dukeImage),
                DialogBox.getDukeDialog(duke.getHelpMessage(), dukeImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        boolean dukeIsRunning = duke.checkIsRunning();
        if (!dukeIsRunning) {
            System.exit(0);
        }
        String input = userInput.getText();
        String response = "";
        try {
            response = duke.getResponse(input);
        } catch (DukeException e) {
            response = e.toString();
        }
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
    }
}
