package duke;

import java.util.concurrent.CompletableFuture;

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
    private static final String GREETING_MESSAGE = "Greetings! I am Intrubot! \n"
        + "Here are some commands to get started: \n"
        + "Adding Items: \n"
        + "  todo <description> \n"
        + "  deadline <description> /by <YYYY-MM-DD> \n"
        + "  event <description> /at <YYYY-MM-DD> \n"
        + "  trivia <question> /ans <ans> \n"
        + "Manipulating Items: \n"
        + "  delete <index> \n"
        + "  done <index> \n"
        + "  clear \n"
        + "Querying Items: \n"
        + "  list \n"
        + "  find <query> \n"
        + "Exit: \n"
        + "  bye";

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Duke duke;
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/john.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/cicero.png"));

    /***
     * Initialises the dialogContainer, initial greeting message from Duke.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.prefWidthProperty().bind(scrollPane.widthProperty().add(-15));
        dialogContainer.prefHeightProperty().bind(scrollPane.heightProperty());
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(GREETING_MESSAGE, dukeImage)
        );
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
        String response;
        try {
            response = duke.getResponse(input);
        } catch (DukeException e) {
            response = Ui.showError(e.getMessage());
        }
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
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
        userInput.clear();
    }
}

