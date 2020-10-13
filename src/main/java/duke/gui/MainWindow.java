package duke.gui;

import java.io.IOException;

import duke.Duke;
import duke.exception.DukeException;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

public class MainWindow {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private Button sendButton;
    @FXML
    private TextField userInput;

    private Duke duke;
    private javafx.scene.image.Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private javafx.scene.image.Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty()
            .bind(dialogContainer.heightProperty());
    }

    public void setDuke(Duke duke) {
        this.duke = duke;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String userText = userInput.getText();
        String fullCommand = userText.trim();
        String dukeText = null;
        try {
            dukeText = duke.getResponse(userText);
        } catch (DukeException e) {
            dukeText = e.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        DialogBox mUser = DialogBox.getUserDialog(userText, userImage);
        DialogBox mDuke = DialogBox.getDukeDialog(dukeText, dukeImage);
        dialogContainer.getChildren()
                .addAll(mUser, mDuke);
        checkIfExit(fullCommand);
        userInput.clear();
    }

    private void checkIfExit(String fullCommand) {
        if (fullCommand.equals("bye")) {
            Platform.exit();
        }
    }
}
