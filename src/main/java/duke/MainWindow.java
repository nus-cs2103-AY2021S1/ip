package duke;

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

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/meme.png"));
    private final Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/meme2.png"));

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
        printUserDialog(input);
        duke.handleInput(input);
        userInput.clear();
    }

    /**
     * Display duke statement on window.
     *
     * @param statement display the input statement.
     */
    public void printDukeDialog(String statement) {
        dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(statement, dukeImage));
    }

    /**
     * Display user statement on window.
     *
     * @param statement display the input statement.
     */
    public void printUserDialog(String statement) {
        dialogContainer.getChildren().addAll(DialogBox.getUserDialog(statement, userImage));
    }
}
