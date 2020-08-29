package duke.controller;

import duke.Duke;
import duke.command.Command;
import duke.control.DialogueBox;
import duke.core.Parser;
import duke.core.Result;
import duke.handle.CommandNotFoundException;
import duke.handle.TaskNotFoundException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;

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

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

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
        Result response = duke.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogueBox.getUserDialogueBox(input, userImage),
                DialogueBox.getDukeDialogueBox((String) response.getMessage(), dukeImage)
        );
        userInput.clear();
        if(!(response.isContinuing())) {
            Stage stage = (Stage) this.getScene().getWindow();
            stage.close();
        }
    }
}
