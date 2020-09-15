package duke.gui;

import static duke.ui.Ui.DIALOG_WELCOME;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import duke.Duke;
import duke.commands.Command;
import duke.commands.ExceptionCommand;
import javafx.application.Platform;
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

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/Mocha.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/Milk.png"));
    private Image dukeImageException = new Image(this.getClass().getResourceAsStream("/images/Milk Harh.png"));
    private Image dukeImageBye = new Image(this.getClass().getResourceAsStream("/images/Milk Bye.png"));

    /** Initializes Main Window with a welcome message. */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().add(
            DialogBox.getDukeDialog(DIALOG_WELCOME, dukeImage)
        );
    }

    /** Sets the Duke class.
     *
     * @param d The Duke class.
     */
    public void setDuke(Duke d) {
        duke = d;
    }

    /** Creates two dialog boxes, one echoing user input and the other containing Duke's reply,
     * and then appends them to the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        Command response = duke.getResponse(input);
        if (response.isExit()) {
            dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response.getDialog(), dukeImageBye)
            );
            ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
            Runnable task = Platform::exit;
            scheduler.schedule(task, 3, TimeUnit.SECONDS);
            return;
        }
        dialogContainer.getChildren().addAll(
            DialogBox.getUserDialog(input, userImage),
            DialogBox.getDukeDialog(response.getDialog(),
                    response instanceof ExceptionCommand ? dukeImageException : dukeImage)
        );
        userInput.clear();
    }
}
