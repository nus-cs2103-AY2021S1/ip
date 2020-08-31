package GUI;

import Duke.Duke;
import Duke.Storage;
import Duke.TaskList.TaskList;
import Duke.UI.UI;
import Duke.DukeExceptions;
import Duke.commands.Parser;
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

    private String greeting = Duke.getGreeting();

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.jpg"));

    @FXML
    public void initialize() {
        Storage.createNewFile();
        try {
            Parser.readSave(Storage.getTmpFile());
        } catch (DukeExceptions e) {
            dialogContainer.getChildren().addAll(
                    DialogBox.getDukeDialog(e.getMessage(), dukeImage),
                    DialogBox.getDukeDialog(TaskList.getListView(), dukeImage)
            );
        }
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(greeting, dukeImage));
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
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
        if (input.equals("bye")) {
            UI.stop();
            Storage.writeToFile();
            System.exit(0);
        }
    }
}