package duke;

import duke.parser.Parser;
import duke.storage.TaskListStorage;
import duke.task.TaskList;
import duke.ui.DialogBox;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

// TODO: separate UI and processing logic. Ideally turn this into a Ui-like class that can be passed to Duke, so that
// Duke can use either a CLI or GUI. May need to reconsider design of Ui and Duke.
/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane implements Ui {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.jpg"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/duke.png"));

    // non-ui
    private boolean shouldStop;
    private TaskList taskList;

    /**
     * Creates a new MainWindow.
     */
    public MainWindow() {
        shouldStop = false;
    }

    /**
     * This method is called by FXMLLoader after the root element has been completely processed. It should not be
     * manually called. Setup code which requires the root node or its children to be initialised is put here.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        // TODO: currently broken due to non-monospace font
        // String logo =
        //     " ____        _        \n"
        //         + "|  _ \\ _   _| | _____ \n"
        //         + "| | | | | | | |/ / _ \\\n"
        //         + "| |_| | |_| |   <  __/\n"
        //         + "|____/ \\__,_|_|\\_\\___|\n";
        // sayLine(logo);
        say("Hello, I'm Duke. What can I do for you?");
        taskList = new TaskListStorage("data/tasks.txt").load(this);
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        userInput.clear();
        dialogContainer.getChildren().addAll(DialogBox.getUserDialog(input, userImage)); // show user input
        Parser.parse(input).execute(this, taskList);
        if (shouldStop) {
            Platform.exit();
        }
    }

    @Override
    public void stop() {
        shouldStop = true;
    }

    @Override
    public void say(String string) {
        dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(string, dukeImage));
    }
}
