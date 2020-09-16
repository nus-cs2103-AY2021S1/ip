
package graphic_interface;

import duke.Duke;
import duke.DukeException;
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
 * Controller for graphicInterface.MainWindow. Provides the layout for the other controls.
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

    private boolean isFinished;

    private Duke duke;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.jpg"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));
    private Image angerImage = new Image(this.getClass().getResourceAsStream("/images/Anger.jpg"));
    private Runnable closeWindowFunction;

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke(Duke d) {
        duke = d;
    }

    public void welcomeMessage() {
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(duke.getWelcome(), dukeImage));
    }

    public void setTerminateFunction(Runnable function) {
        this.closeWindowFunction = function;
    }

    private void setFinished() {
        this.isFinished = true;
    }

    private void countDown(int secondsLeft) {
        assert (secondsLeft > 0) : "Negative seconds left in MainWindow.countDown()";
        if (secondsLeft == 0) {
            this.closeWindowFunction.run();
            return;
        }
        String countDownMessage = "....." + secondsLeft + ".....";
        //@@author John_D
        //Slight Modification from his solution on
        //https://stackoverflow.com/questions/27334455/how-to-close-a-stage-after-a-certain-amount-of-time-javafx.
        PauseTransition delay = new PauseTransition(Duration.seconds(1));
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(countDownMessage, dukeImage));
        delay.setOnFinished(event -> this.countDown(secondsLeft - 1));
        delay.play();
    }

    @FXML
    public void showErrorMessage() {
        String errorMessage = "Unexpected Error Occurred";
        dialogContainer.getChildren().add(DialogBox.getDukeWarning(errorMessage, angerImage));
    }

    private void echoUserInput(String input) {
        dialogContainer.getChildren().add(DialogBox.getUserDialog(input, userImage));
    }


    /**
     * Creates two dialog boxes, one echoing user input and the other containing duke.Duke's reply and then
     * appends them to the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        this.echoUserInput(input);
        try {
            String response = duke.processInput(input, () -> this.setFinished());
            dialogContainer.getChildren().addAll(
                    DialogBox.getDukeDialog(response, dukeImage)
            );
        } catch (DukeException err) {
            dialogContainer.getChildren().add(DialogBox.getDukeWarning(err.getMessage(), angerImage));
        } finally {
            userInput.clear();
            if (this.isFinished) {
                this.countDown(3);
            }
        }
    }
}