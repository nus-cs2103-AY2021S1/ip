package duke.gui;

import duke.Ui;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for MainWindow. Provides the layout for other controls.
 */
public class MainWindow extends AnchorPane {

    /** GUI for Duke. */
    private Main dukeGui;

    private boolean isFirstLaunch = true;

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private final Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * Initializes the main window.
     * Displays the introduction message if this is the first launch.
     */
    @FXML
    public void initialize() {
        this.scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());

        if (isFirstLaunch) {
            isFirstLaunch = false;
            this.dialogContainer
                    .getChildren()
                    .add(DialogBox.getDukeDialog(new Ui(true).displayIntroduction(), this.dukeImage));
        }
    }

    /**
     * Sets GUI for Duke.
     *
     * @param dukeGui GUI for Duke.
     */
    public void setDukeGui(Main dukeGui) {
        this.dukeGui = dukeGui;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply.
     * Appends them to the dialog container.
     * Clears the user input after processing.
     */
    @FXML
    private void updateDialogContainer() {
        // Initialize labels
        String userText = this.userInput.getText();
        String dukeText = this.dukeGui.getResponse(userText);

        // Add children to dialog container
        this.dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, this.userImage),
                DialogBox.getDukeDialog(dukeText, this.dukeImage)
        );

        // Clear text field
        this.userInput.clear();
    }
}
