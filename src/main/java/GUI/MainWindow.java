package GUI;

import Duke.Duke;
import Duke.Storage;
import Duke.TaskList.TaskList;
import Duke.UI.UI;
import Duke.DukeExceptions;
import Duke.commands.Parser;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
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
    private TextArea userInput;
    @FXML
    private Button sendButton;

    private Duke duke;

    private String greeting = Duke.getGreeting();

    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.jpg"));

    @FXML
    public void initialize() {
        Storage.createNewFile();
        userInput.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.ENTER) {
                    String text = userInput.getText();
                    userInput.setText(text.substring(0, text.length() - 1));
                    handleUserInput();
                }
            }
        });
        try {
            Parser.readSave(Storage.getTmpFile());
        } catch (DukeExceptions e) {
            dialogContainer.getChildren().add(DukeDialogBox.getDukeDialog(e.getMessage(), dukeImage));
        }
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().add(DukeDialogBox.getDukeDialog(greeting + "\n" + TaskList.getListView(), dukeImage));
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
        UserDialogBox userDialog = UserDialogBox.getUserDialog(input);
        userDialog.setMinHeight(Region.USE_PREF_SIZE);
        DukeDialogBox dukeDialog = DukeDialogBox.getDukeDialog(response, dukeImage);
        dukeDialog.setMinHeight(Region.USE_PREF_SIZE);
        dialogContainer.getChildren().addAll(
                userDialog,
                dukeDialog
        );
        userInput.clear();
    }
}