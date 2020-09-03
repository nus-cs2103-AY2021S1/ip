package duke.ui.gui;

import duke.Duke;
import duke.DukeCommandMatcher;
import duke.storage.Storage;
import duke.ui.Printer;
import duke.ui.gui.DialogBox;
import duke.utils.Constants;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.util.Objects;

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

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DonaldTrump.jpg"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/KimJongUn.jpg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(Constants.GREETING, dukeImage));
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
        //handle input
        dialogContainer.getChildren().add(DialogBox.getUserDialog(input, userImage));
        handleDukeOutput(input);
        userInput.clear();
    }

    private void handleDukeOutput(String input) {
        try {
            Storage database = new Storage("data/tasksTable.csv");
            DukeCommandMatcher parser = new DukeCommandMatcher(database);
            String response = parser.matchCommand(input);
            if (Objects.equals(response, "EXIT")) {
                dialogContainer.getChildren().add(DialogBox.getDukeDialog(Printer.printBye(), dukeImage));
            }
            dialogContainer.getChildren().add(DialogBox.getDukeDialog(response, dukeImage));
        } catch (Exception e) {
            dialogContainer.getChildren().add(DialogBox.getDukeDialog(e.toString(), dukeImage));
        }
    }
}