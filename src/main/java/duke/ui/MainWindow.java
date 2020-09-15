package duke.ui;

import java.util.function.Consumer;

import duke.Duke;
import javafx.application.Platform;
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
public class MainWindow extends AnchorPane implements Ui {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.jpg"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/duke.png"));

    // non-ui
    private boolean shouldStop;
    private Consumer<String> inputHandler;

    /**
     * Creates a new MainWindow.
     */
    public MainWindow() {
        shouldStop = false;
        inputHandler = s -> {};
    }

    /**
     * This method is called by FXMLLoader after the root element has been completely processed. It should not be
     * manually called. Setup code which requires the root node or its children to be initialised is put here.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        new Duke(this); // TODO: think of a nicer way to do this
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        userInput.clear();
        dialogContainer.getChildren().addAll(DialogBox.getUserDialog(input, userImage)); // show user input
        inputHandler.accept(input);
        if (shouldStop) {
            Platform.exit();
        }
    }

    @Override
    public void setInputHandler(Consumer<String> function) {
        inputHandler = function;
    }

    @Override
    public void stop() {
        shouldStop = true;
    }

    @Override
    public void say(String string, boolean isError) {
        dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(string, dukeImage, isError));
    }
}
