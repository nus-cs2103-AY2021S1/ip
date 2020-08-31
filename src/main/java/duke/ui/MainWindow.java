package duke.ui;

import duke.Duke;
import duke.command.CommandResponse;
import duke.exception.DukeException;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    /**
     * The User image.
     */
    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    /**
     * The Duke image.
     */
    private final Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));
    /**
     * The Ui.
     */
    private final Ui ui = new Ui();
    /**
     * The Scroll pane.
     */
    @FXML
    private ScrollPane scrollPane;
    /**
     * The Dialog container.
     */
    @FXML
    private VBox dialogContainer;
    /**
    * The Send button.
    */
    @FXML
    private Button sendButton;
    /**
     * The User input.
     */
    @FXML
    private TextField userInput;
    /**
     * The Duke.
     */
    private Duke duke;

    /**
     * Initialise the scroll pane and dialog container.
     */
    @FXML
    public void initialize() {
        this.scrollPane.vvalueProperty().bind(this.dialogContainer.heightProperty());
        this.showWelcomeMessage();
    }

    /**
     * Sets duke.
     *
     * @param duke the duke
     */
    public void setDuke(Duke duke) {
        this.duke = duke;
    }

    /**
     * Show welcome message.
     */
    @FXML
    private void showWelcomeMessage() {
        this.dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(this.ui.getWelcomeMessage(), this.dukeImage)
        );
        this.userInput.clear();
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then
     * appends them to the dialog container.
     * Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = this.userInput.getText();
        CommandResponse response = new CommandResponse();
        String message;
        try {
            response = this.duke.execute(this.userInput.getText());
            message = response.getMessage();
        } catch (DukeException ex) {
            message = this.ui.getDukeExceptionMessage(ex);
        }

        this.dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, this.userImage),
                DialogBox.getDukeDialog(message, this.dukeImage)
        );
        this.userInput.clear();

        if (response.isExit()) {
            Platform.exit();
        }
    }
}