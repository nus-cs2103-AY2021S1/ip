package duke.gui;

import duke.Duke;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.VBox;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    private static final String TOPLINE = "----------------- DUKE -----------------\n";
    private static final String BOTTOMMINE = "\n------------------------------------------";
    private static final String ERRORLINE = "----------------- ERROR ----------------\n";
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Duke duke;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/MortyUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/ShrekDuke.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     * Exits the Duke application when user inputs 'bye'.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String dukeOutput = getDukeResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(dukeOutput, dukeImage)
        );
        userInput.clear();
        if (input.equals("bye")) {
            exitDuke();
        }
    }

    /**
     * Gets the response from Duke and customise the output text based on whether a DukeException was thrown.
     *
     * @param input the input given by user.
     * @return dukeOutput the output that duke will respond to the user with.
     */
    private String getDukeResponse(String input) {
        String dukeOutput = "";
        String response = duke.getResponse(input);
        if (duke.getIsResponseDukeException()) {
            dukeOutput = ERRORLINE + response + BOTTOMMINE;
            return dukeOutput;
        }
        dukeOutput = TOPLINE + response + BOTTOMMINE;
        return dukeOutput;
    }

    private void exitDuke() {
        Timer exit = new Timer();
        exit.schedule(new TimerTask() {
            public void run() {
                System.exit(0);
            }
        }, 500);
    }
}