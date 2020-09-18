import java.io.FileNotFoundException;

import javafx.fxml.FXML;
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

    private Duke duke;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/greg.jpg"));

    /**
     * Initialises the Dialog Container with the opening message
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        String help = "Hello I'm Greg! Here are my list of commands: \n"
                + "list: shows current list of tasks \n"
                + "done {number}: marks the number position task as done \n"
                + "todo {description}: adds a Todo task \n"
                + "deadline {description} /by {time}: adds a Deadline Task \n"
                + "event {description} /at {time}: adds an Event Task \n"
                + "delete {number}: deletes the number position task \n"
                + "delete {number-number}: deletes the range of Tasks \n"
                + "find {keyword}: finds all tasks with keyword in it \n"
                + "undo: undo your most recent action \n"
                + "bye: exits the Greg Bot \n";
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(help, dukeImage));
    }

    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() throws FileNotFoundException, DukeException {
        String input = userInput.getText();
        String response = duke.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
    }
}
