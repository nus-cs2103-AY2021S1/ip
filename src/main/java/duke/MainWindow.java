package duke;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    private static final String START_MSG = "Duke:\nHello! I'm Duke.\nWhat can I do for you?";
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Duke duke;
    private Runnable exitFunction;

    private final Image userImage = new Image(getClass().getResource("/images/User.png").toString());
    private final Image dukeImage = new Image(getClass().getResource("/images/Duke.png").toString());

    @FXML
    void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        generateStartMsg();
    }

    private void generateStartMsg() {
        dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(START_MSG, dukeImage));
    }

    public void setDuke(Duke d) {
        duke = d;
    }

    public void setExitFunction(Runnable exitFunction) {
        this.exitFunction = exitFunction;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    protected void handleUserInput() {
        String input = userInput.getText();
        String response = duke.getResponse(input);

        if (input.equals("")) {
            return;
        }

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );

        userInput.clear();

        if (duke.shouldExit()) {
            userInput.setDisable(true);
            PauseTransition delay = new PauseTransition(Duration.seconds(1));
            delay.setOnFinished(event -> exitFunction.run());
            delay.play();
        }
    }
}
