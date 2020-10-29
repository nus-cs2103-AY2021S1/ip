package duke;

import duke.ui.DialogBox;
import duke.ui.Ui;
import duke.ui.UserDialogBox;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.util.Timer;
import java.util.TimerTask;

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

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/JohnnyBravo.jpg"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/MrsDino.jpg"));

    /**
     * Initialises the GUI.
     */
    @FXML
    public void initialize() {
        Ui ui = new Ui();
        dialogContainer.getChildren().addAll(
                DialogBox.getMrsDinoDialog(ui.getWelComeMessage(), dukeImage)
        );
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duke.getResponse(input);
        dialogContainer.getChildren().addAll(
                UserDialogBox.getUserDialog(input, userImage),
                DialogBox.getMrsDinoDialog(response, dukeImage)
        );
        if (input.equals("bye")) {
            new Timer().schedule(new TimerTask() {
                public void run () {
                    handleExit();
                }
            }, 1000);
        }
        userInput.clear();
    }

    /**
     * Exits the program.
     */
    @FXML
    private void handleExit() {
        Platform.exit();
        System.exit(0);
    }
}
