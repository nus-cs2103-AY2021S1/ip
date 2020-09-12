package duke;

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
    // @FXML marks a private or protected member and makes it accessible to FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Duke duke;
    private boolean isExit = false;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/Shirogane.jpg"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/Shinomiya.jpg"));

    public void setDukeAndDisplayWelcome(Duke d) {
        duke = d;

        String response = duke.getWelcome();
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(response, dukeImage)
        );
    }

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        // After user says "bye", and gives another input e.g. "some_text", the application will exit.
        if (isExit) {
            // get a handle to the stage
            Stage stage = (Stage) scrollPane.getScene().getWindow();
            stage.close();
        }

        String input = userInput.getText();
        String response = duke.getResponse(input);
        isExit = duke.isExit(input);

        dialogContainer.getChildren().addAll(
            DialogBox.getUserDialog(input, userImage),
            DialogBox.getDukeDialog(response, dukeImage)
        );

        userInput.clear();
    }
}
