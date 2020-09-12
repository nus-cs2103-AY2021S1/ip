package duke.ui;

import duke.Duke;
import duke.Response;
import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.UnknownCommand;
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

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image dukeSmileImage = new Image(this.getClass().getResourceAsStream("/images/DukeSmile.png"));
    private Image dukeBigSmileImage = new Image(this.getClass().getResourceAsStream("/images/DukeBigSmile.png"));
    private Image dukeOopsImage = new Image(this.getClass().getResourceAsStream("/images/DukeOops.png"));
    private Image dukeImage = dukeSmileImage;

    /**
     * Initializes certain properties of the MainWindow.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(UiForGui.showWelcome(), dukeSmileImage));
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
        Response response = duke.getResponse(input);
        String responseString = response.getResponseString();
        Command responseCommand = response.getCommand();
        setDukeImage(responseCommand);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(responseString, dukeImage)
        );
        userInput.clear();
    }

    private void setDukeImage(Command responseCommand) {
        if (responseCommand instanceof AddCommand || responseCommand instanceof DeleteCommand
                || responseCommand instanceof FindCommand || responseCommand instanceof ListCommand) {
            dukeImage = dukeSmileImage;
        } else if (responseCommand instanceof DoneCommand || responseCommand instanceof ExitCommand) {
            dukeImage = dukeBigSmileImage;
        } else if (responseCommand instanceof UnknownCommand) {
            dukeImage = dukeOopsImage;
        } else {
            assert false : "Command entered was not recognised.";
        }
    }
}
