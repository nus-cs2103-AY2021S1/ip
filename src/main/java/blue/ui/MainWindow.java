package blue.ui;

import java.util.Timer;
import java.util.TimerTask;

import blue.Blue;
import blue.command.CommandResponse;
import blue.exception.BlueException;
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
    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    /**
     * The Blue image.
     */
    private final Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/blue.png"));
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
     * The Blue.
     */
    private Blue blue;

    /**
     * Initialise the scroll pane and dialog container.
     */
    @FXML
    public void initialize() {
        this.scrollPane.vvalueProperty().bind(this.dialogContainer.heightProperty());
        this.showWelcomeMessage();
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
     * Sets blue.
     *
     * @param blue the blue
     */
    public void setDuke(Blue blue) {
        this.blue = blue;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Blue's reply and then
     * appends them to the dialog container.
     * Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = this.userInput.getText();
        CommandResponse response = new CommandResponse();
        String message;
        try {
            response = this.blue.execute(this.userInput.getText());
            message = response.getMessage();
        } catch (BlueException ex) {
            message = this.ui.getDukeExceptionMessage(ex);
        }

        this.dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, this.userImage),
                DialogBox.getDukeDialog(message, this.dukeImage)
        );
        this.userInput.clear();

        if (response.isExit()) {
            exit();
        }
    }

    /**
     * Exit.
     */
    @FXML
    private void exit() {
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Platform.exit();
            }
        };
        Timer timer = new Timer();

        long delay = 1000L; // 1 second
        timer.schedule(task, delay * 4);
    }
}
