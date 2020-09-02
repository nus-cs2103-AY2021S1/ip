import Parser.InputManager;
import Tasks.TaskManager;
import UI.UserInterface;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.File;
import java.io.IOException;

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

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        String fileDir = "./DukeTodoSave.txt";
        InputManager.fileDir(fileDir);
        File save = new File(fileDir);
        if (!save.exists()) { // create the text file
            dialogContainer.getChildren().addAll(
                    DialogBox.getDukeDialog("Save file does not exist, creating it now!", dukeImage));
            try {
                save.createNewFile();
            } catch (IOException e) {
                dialogContainer.getChildren().addAll(
                        DialogBox.getDukeDialog("Error creating the save file!" + System.lineSeparator()
                                + e, dukeImage));
            }
        }
        TaskManager.load(save);
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(UserInterface.welcomeMessage(), dukeImage)
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
        String response = Duke.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
    }
}