package duke.gui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

/**
 * Controller for <code>Node</code> that represents the frame of the main window of the application.
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

    /**
     * Reference to <code>DukeGui</code> class that manages the main logic.
     */
    private DukeGui dukeGui;

    /**
     * User avatar image.
     */
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));

    /**
     * Duke avatar image.
     */
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/dukebrain.jpg"));

    /**
     * Default font for user.
     */
    private Font userFont = Font.font("Courier New", 12);

    /**
     * Default font for Duke.
     */
    private Font dukeFont = Font.font("Consolas", 12);

    /**
     * Sets <code>dukeGui</code>.
     *
     * @param d Given <code>DukeGui</code> object.
     */
    public void setDukeGui(DukeGui d) {
        dukeGui = d;
    }

    /**
     * Initialization of <code>Listener</code> that sets the window to the latest messages.
     */
    @FXML
    private void initialize() {
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }

    /**
     * Creates a <code>DialogBox</code> containing the user's input and passes the input for processing.
     * User input is then cleared.
     *
     * This method is triggered on user entering and sending a new user input.
     *
     * If a "bye" command is parsed and executed, the program will exit.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();

        // Echos user input in GUI
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage, userFont)
        );

        // Close application if exit is detected
        if (dukeGui.handleUserInput(input)) {
            Platform.exit();
        }

        userInput.clear();
    }

    /**
     * Creates a <code>DialogBox</code> for a given response from user.
     *
     * @param response Generated response from Duke's command handling modules.
     */
    public void handleDukeResponse(String response) {
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(response, dukeImage, dukeFont)
        );
    }

    /**
     * Creates a <code>DialogBox</code> for a given warning from Duke.
     *
     * @param warning Warning generated when Duke tried to handle user input.
     */
    public void handleDukeWarning(String warning) {
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeWarningDialog(warning, dukeImage, dukeFont)
        );
    }


}
