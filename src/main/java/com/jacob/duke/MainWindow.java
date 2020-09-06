package main.java.com.jacob.duke;

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

    private final Image USER_IMAGE = new Image(new FileInputStream(System.getProperty("user.dir")
            + "\\data\\userChatBotIcon.png"));
    private final Image DUKE_IMAGE = new Image(new FileInputStream(System.getProperty("user.dir")
            + "\\data\\dukeChatBotIcon.png"));

    public MainWindow() { }

    /**
     * Initializes the main window with a welcome message.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        String startUpString = "Hi there, I am Duke! Here are some commands that you can use to talk with me :)\n"
                + "\n\n\nBasic Commands:\n"
                + "list: gets you list of currently stored tasks\n"
                + "bye: terminates duke\n"
                + "\n\n\nAdd Commands:\n"
                + "todo <task description>: adds todo\n"
                + "event <task description> /at <YYYY-MM-DD>: adds an event\n"
                + "deadline <task description> /by <YYYY-MM-DD>: adds deadline\n"
                + "\n\n\nAdditional Commands:\n"
                + "delete <number>: deletes selected task\n"
                + "done <number>: marks selected task done \n"
                + "list-due <YYYY-MM-DD HHMM>: gets list of tasks due on that date\n"
                + "find: finds any task that has a description matching the given string\n";

        dialogContainer.getChildren().add(DialogBox.getDukeDialog(startUpString, DUKE_IMAGE));
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
        String response = duke.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, USER_IMAGE),
                DialogBox.getDukeDialog(response, DUKE_IMAGE)
        );
        userInput.clear();

        assert (userInput.getText().equals(""));
    }
}
