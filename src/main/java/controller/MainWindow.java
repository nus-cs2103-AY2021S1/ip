package controller;

import java.io.IOException;

import duke.Duke;
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
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Duke duke;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private final Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/duke.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Displays welcome message on starting Duke chat bot.
     */
    public void dukeWelcome() {
        String welcomeMessage = "Hello!!! I'm Duke, your handy tasks manager."
                + "\nHere are some commands you can use!"
                + "\n- To add a todo, type: 'todo <task description>'."
                + "\n- To add a deadline, type: 'deadline <deadline description> /by <dd-mm-yyyy> HHMM'."
                + "\n- To add an event, type: 'event <event description> /at <dd-mm-yyyy> HHMM'."
                + "\n- To view all tasks, type: 'list'."
                + "\n- To mark a task as done, type: 'done <task number>'."
                + "\n- To delete a task, type: 'delete <task number>'."
                + "\n- To undo latest action, type: 'undo'."
                + "\n- To find tasks, type: 'find <keyword>'."
                + "\n- To leave me, type: 'bye'.";
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(welcomeMessage, dukeImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing duke.Duke's reply and then appends them
     * to the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() throws IOException {
        String input = userInput.getText();
        String response = duke.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
    }

    public static void endDuke() {
        Platform.exit();
    }
}
