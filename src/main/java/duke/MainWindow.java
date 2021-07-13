package duke;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 * Controls the MainWindow.
 * Provides the layout for the other controls.
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

    /** The Duke for the GUI */
    private Duke duke;
    /** The image used in the GUI for the user */
    private final Image userImage = new Image(this.getClass()
            .getResourceAsStream("/images/DaUser.png"));
    /** The image used in the GUI for Duke */
    private final Image dukeImage = new Image(this.getClass()
            .getResourceAsStream("/images/DaDuke.png"));

    /**
     * Initializes the MainWindow.
     */
    @FXML
    public void initialize() {
        Ui ui = new Ui();
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(
                ui.generateWelcomeMessage(), dukeImage));
    }

    /**
     * Sets the specified Duke to the Duke in this MainWindow.
     *
     * @param duke the specified Duke.
     */
    public void setDuke(Duke duke) {
        this.duke = duke;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply
     * and then appends them to the dialog container. Clears the user input after processing.
     * If an exit command was entered, there will be a pause of 1 second before the MainWindow closes.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        Response response = duke.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response.getResponse(), dukeImage)
        );
        userInput.clear();

        if (response.isExit()) {
            PauseTransition delay = new PauseTransition(
                    Duration.seconds(1.5));
            delay.setOnFinished(event -> Platform.exit());
            delay.play();
        }
    }
}
