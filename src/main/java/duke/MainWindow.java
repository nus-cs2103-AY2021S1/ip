package duke;

import java.io.IOException;

import duke.task.Task;
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

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/cony_user.png"));
    private final Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/brown_duke.png"));

    /**
     * Loads the start up screen of the application
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().add(DialogBox.getDukeDialog("Hello, I'm Bearimy! How can I help you today?",
                dukeImage));
        if (!Duke.getTasks().getListOfTasks().isEmpty()) {
            String savedTasks = "Welcome back! Here are the tasks in your list:\n";
            for (Task task : Duke.getTasks().getListOfTasks()) {
                savedTasks += task + "\n";
            }
            dialogContainer.getChildren().add(DialogBox.getDialogBox(savedTasks));
        }
    }

    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    public void handleUserInput() throws IOException {
        String input = userInput.getText();
        if (input.equals("bye")) {
            Platform.exit();
        }
        String response = duke.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog("[User] " + input, userImage),
                DialogBox.getDukeDialog("[Bearimy] " + response, dukeImage)
        );
        userInput.clear();
    }
}
