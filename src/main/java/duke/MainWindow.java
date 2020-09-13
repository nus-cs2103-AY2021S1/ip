package duke;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

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

    // user.png: Standing Man icon by Icons8 from https://icons8.com/icon/8NzonSPORfzB/man-in-a-tuxedo
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    // duke.png: Man In A Tuxedo icon by Icons8 from https://icons8.com/icon/21832/standing-man
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/duke.png"));
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
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
        if (input.equals("bye")) {
            Stage stage = (Stage) scrollPane.getScene().getWindow();
            // @@author James_D
            // Reused with minor modifications from
            // https://stackoverflow.com/questions/27334455/how-to-close-a-stage-after-a-certain-amount-of-time-javafx
            PauseTransition delay = new PauseTransition(Duration.seconds(1.5));
            delay.setOnFinished(event -> stage.close());
            delay.play();
            // @@author
        }
        String response = duke.getResponse(input);
        dialogContainer.getChildren().addAll(
            DialogBox.getUserDialog(input, userImage),
            DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
    }

    /**
     *  Shows the greeting message as the first message given by Duke.
     */
    public void showGreeting() {
        dialogContainer.getChildren().addAll(
            DialogBox.getDukeDialog(Ui.getGreeting(), dukeImage)
        );
    }
}
