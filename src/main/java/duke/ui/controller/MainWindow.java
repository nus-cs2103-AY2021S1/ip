package duke.ui.controller;

import duke.exception.DukeException;
import duke.main.Duke;
import duke.tools.Format;
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
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Duke duke;

    //Image is taken from https://wiki.openjdk.java.net/display/duke/Gallery.
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));

    //Image is taken from https://wiki.openjdk.java.net/display/duke/Gallery.
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DukeMask.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }


    /**
     * Sets the Duke object to the given duke.
     *
     * @param duke input Duke object.
     */
    public void setDuke(Duke duke) {
        this.duke = duke;
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(duke.getUi().greet(), dukeImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other
     * containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() throws DukeException {
        String input = userInput.getText();
        Format<String> format = new Format<>(input);
        String response = duke.getResponse(input).toString();
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(format.toString(), userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );

        if (duke.getUi().getParser().isEnd(input)) {
            closeWindow();
        }
        userInput.clear();
    }

    /**
     * Closes the interaction window for
     * when the user types in "bye" command.
     */
    private void closeWindow() {
        //Disable the functions of the user input and send button.
        userInput.setOnAction(null);
        sendButton.setOnAction(null);

        //Pause for a second before exit.
        PauseTransition delay = new PauseTransition(Duration.seconds(1));
        delay.setOnFinished(event -> System.exit(0));
        delay.play();
    }
}
