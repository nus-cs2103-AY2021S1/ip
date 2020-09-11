package duke.controllers;

import duke.Duke;
import duke.Parser;
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
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Duke duke;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser2.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke2.png"));

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
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duke.getResponse(input); // will always be in Duke. Replacing with legit logic later.
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialogBox(input, userImage),
                DialogBox.getDukeDialogBox(response, dukeImage)
        );
        if (Parser.isBye(input)) {
            duke.saveCurrentTasks();
        }

        userInput.clear();
    }

    /**
     * Creates Duke welcome message dialog box.
     */
    @FXML
    public void welcomeUser() {
        String dukeWelcome = duke.getWelcomeMessage();
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialogBox(dukeWelcome, dukeImage)
        );
    }
}
