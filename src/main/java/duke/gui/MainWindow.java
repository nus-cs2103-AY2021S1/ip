package duke.gui;

import duke.Duke;
import duke.utils.DukeState;
import duke.utils.DukeStdMsg;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
/**
 * Controller for duke.gui.MainWindow. Provides the layout for the other controls.
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

    /**
     * Sets scrollpane to scroll to the bottom whenever new dialogs are added.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Sets the Duke object. Prints a welcome text based on whether Duke has loaded data from disk.
     * @param d The Duke object.
     */
    public void setDuke(Duke d) {
        duke = d;

        StringBuilder sb = new StringBuilder();
        if (duke.isLoadedFromDisk()) {
            sb.append(DukeStdMsg.LOAD_FROM_DISK.getMsg());
        } else {
            sb.append(DukeStdMsg.FRESH_START.getMsg());
        }
        sb.append(DukeStdMsg.WELCOME.getMsg());

        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(sb.toString(), dukeImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing. If Duke has exited, it disables userInput and
     * the sendButton.
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

        if (duke.getDukeState() == DukeState.EXITED) {
            userInput.setDisable(true);
            sendButton.setDisable(true);
        }
    }
}