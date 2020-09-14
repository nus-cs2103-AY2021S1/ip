package main.java.com.jacob.duke.javafx;
import java.util.concurrent.CompletableFuture;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import main.java.com.jacob.duke.Duke;


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

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/userChatBotIcon.png"));
    private final Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/dukeChatBotIcon.png"));
    private final String startString = "Whats up young blood. Kobe here. How can I help you today?\n"
            + "\n Basic Requests:\n"
            + "  list: gets you list of currently stored tasks\n"
            + "  bye: terminates our conversation\n"
            + "\n\n Add Request:\n"
            + "  todo <task description>: adds todo\n"
            + "  event <task description> /at <YYYY-MM-DD 2359>: adds an event\n"
            + "  deadline <task description> /by <YYYY-MM-DD 2359>: adds deadline\n"
            + "\n\n Additional Commands:\n"
            + "  delete <number>: deletes selected task\n"
            + "  done <number>: marks selected task done \n"
            + "  list-due <YYYY-MM-DD 2359>: gets list of tasks due on that date\n"
            + "  find: finds any task that has a description matching the given string\n"
            + "  note <question>? <answer>: Adds a flashcard type note\n"
            + "  note-list: gets list of note\n"
            + "  note-delete <index>: delete note at specified index on list\n";
    public MainWindow() { }

    /**
     * Initializes the main window with a welcome message.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(startString, dukeImage));
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
        String input = userInput.getText().trim();
        String response = duke.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();

        CompletableFuture.runAsync(() -> {
            try {
                if (input.equals("bye")) {
                    Thread.sleep(1000);
                    System.exit(0);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.exit(1);
            }
        });

        assert (userInput.getText().equals(""));
    }
}
