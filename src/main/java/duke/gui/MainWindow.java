package duke.gui;

import duke.Duke;
import duke.exception.DukeException;
import duke.response.Response;
import duke.storage.Storage;
import duke.ui.Ui;
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
     * File name of the file where the current task list is saved (under ./data/ directory)
     */
    private static final String FILE_STRING = "duke.txt";
    private Storage storage;

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;


    private Duke duke;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private final Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * Initializes the main window by setting up scroll properties and showing greetings
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dukePrint(Ui.getGreetings());
        createStorage();
    }

    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Creates storage to store text output
     */
    public void createStorage() {
        try {
            this.storage = Storage.create(FILE_STRING);
        } catch (DukeException e) {
            dukePrint(e.getMessage());
        }
    }

    /**
     * Prints message on User's dialog
     *
     * @param message to be printed
     */
    public void userPrint(String message) {
        dialogContainer.getChildren().add(DialogBox.getUserDialog(message, userImage));
    }

    /**
     * Prints message on Duke's dialog
     *
     * @param message to be printed
     */
    public void dukePrint(String message) {
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(message, dukeImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    public void handleUserInput() {
        try {
            String userText = userInput.getText();
            Response dukeResponse = getResponse(userInput.getText());
            userPrint(userText);
            dukePrint(dukeResponse.getText());
            userInput.clear();

            if (dukeResponse.getExit()) {
                storage.write(duke.getTaskList());
                storage.close();
                System.exit(0);
            }
        } catch (DukeException e) {
            dukePrint(e.getMessage());
        }
    }

    public Response getResponse(String input) {
        return duke.runCommand(input);
    }
}
