import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
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

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));
    private Image backgroundImage = new Image(this.getClass().getResourceAsStream("/images/background.png"));

    private BackgroundImage backgroundImageCreated = new BackgroundImage(backgroundImage,
            BackgroundRepeat.REPEAT,
            BackgroundRepeat.REPEAT,
            BackgroundPosition.DEFAULT,
            BackgroundSize.DEFAULT);

    private Background background = new Background(backgroundImageCreated);

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.setBackground(background);
    }

    public void setDuke(Duke d) {
        duke = d;
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(d.showWelcomeMessage(), dukeImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duke.getResponse(input);
        dialogContainer.getChildren().addAll(DialogBox.getUserDialog(input, userImage));

        // delay duke's output to come awhile after user's input
        PauseTransition delayDukeOutput = new PauseTransition(Duration.seconds(0.3));
        delayDukeOutput.setOnFinished(
                event -> dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(response, dukeImage)));
        delayDukeOutput.play();

        userInput.clear();

        // delay exiting program
        if (input.equals("bye")) {
            PauseTransition wait = new PauseTransition(Duration.seconds(1));
            wait.setOnFinished(event -> Platform.exit());
            wait.play();
        }
    }
}
