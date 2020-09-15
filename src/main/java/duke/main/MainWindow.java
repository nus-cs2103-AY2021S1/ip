package duke.main;

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
    private Stage stage;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/cat.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/ducky.png"));

    /**
     * Initializes the main window and prints out greeting message.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        this.greeting();
    }

    public void setDuke(Duke d) {
        duke = d;
    }

    public void setStage(Stage s) {
        this.stage = s;
    }

    private void greeting() {
        String greetLine = "Hi Master, Duke here";
        DialogBox greetDialog = DialogBox.getDukeDialog(greetLine, dukeImage);
        dialogContainer.getChildren().addAll(greetDialog);
    }

    private void exit(int duration) {
        PauseTransition delay = new PauseTransition(Duration.seconds(duration));
        delay.setOnFinished(event -> this.stage.hide());
        delay.play();
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duke.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
        if (input.equals("bye")) {
            int secondToClose = 2;
            this.exit(secondToClose);
        }
    }
}
