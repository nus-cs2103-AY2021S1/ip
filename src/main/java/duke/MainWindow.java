package duke;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
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
    private static final double DELAY_CLOSE = 1.0;
    private static final double DELAY_RESPONSE = 0.3;
    private static final double DELAY_TRANSITION = 0.2;

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
        addDukeResponse(DialogBox.getDukeDialog(START_MSG, dukeImage));
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
        String input = userInput.getText().trim();
        String response = duke.getResponse(input);

        if (input.equals("")) {
            return;
        }

        userInput.clear();

        DialogBox userDialog = DialogBox.getUserDialog(input, userImage);
        addUserDialog(userDialog);

        DialogBox dukeResponse = DialogBox.getDukeDialog(response, dukeImage);
        addDukeResponse(dukeResponse);

        if (duke.shouldExit()) {
            userInput.setDisable(true);
            PauseTransition delay = new PauseTransition(Duration.seconds(DELAY_CLOSE));
            delay.setOnFinished(event -> exitFunction.run());
            delay.play();
        }
    }

    private void addUserDialog(DialogBox userDialog) {
        userDialog.translateXProperty().set(-1 * dialogContainer.getWidth());
        dialogContainer.getChildren().addAll(userDialog);

        KeyValue keyValue = new KeyValue(userDialog.translateXProperty(), 0, Interpolator.EASE_IN);
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(DELAY_TRANSITION), keyValue);
        Timeline timeline = new Timeline(keyFrame);
        timeline.play();
    }

    private void addDukeResponse(DialogBox dukeResponse) {
        PauseTransition delayResponse = new PauseTransition(Duration.seconds(DELAY_RESPONSE));
        delayResponse.setOnFinished(event -> {
            dukeResponse.translateXProperty().set(1 * dialogContainer.getWidth());
            dialogContainer.getChildren().addAll(dukeResponse);

            KeyValue keyValue = new KeyValue(dukeResponse.translateXProperty(), 0, Interpolator.EASE_IN);
            KeyFrame keyFrame = new KeyFrame(Duration.seconds(DELAY_TRANSITION), keyValue);
            Timeline timeline = new Timeline(keyFrame);
            timeline.play();
        });
        delayResponse.play();

    }
}
