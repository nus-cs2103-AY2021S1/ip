package duke.gui.controller;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import duke.core.DukeData;
import duke.core.DukeLogic;
import duke.core.task.Task;
import duke.gui.IndexListCell;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * GUI application for Duke (Duke GUI)
 */
public class MainWindow extends BorderPane {

    private static final String WELCOME_MESSAGE = "Welcome, my little penguins!";

    @FXML
    private VBox dialogContainer;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private TextField inputField;
    @FXML
    private ListView<Task> taskView;

    private final Image dukeAvatar = new Image(getResource("images/duke.jpg"));
    private final Image userAvatar = new Image(getResource("images/user.jpg"));
    private final Image errorAvatar = new Image(getResource("images/error.jpg"));

    private ByteArrayOutputStream outputStream;
    private ByteArrayOutputStream errorStream;

    private DukeData dukeData;

    /**
     * Initialize all Duke GUI items
     */
    @FXML
    public void initialize() {
        // Redirect System.out to outputStream
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Redirect System.err to errorStream
        errorStream = new ByteArrayOutputStream();
        System.setErr(new PrintStream(errorStream));

        // Initialize Duke variables
        ObservableList<Task> taskList = FXCollections.observableArrayList();
        dukeData = new DukeData(taskList);

        // Initialize taskView
        taskView.setItems(taskList);
        taskView.setCellFactory(listView -> new IndexListCell<>());

        // Autoscroll
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());

        // Print greeting
        clearDisplay();
    }

    /**
     * Get application resource
     * @param path Path from resource folder
     * @return InputStream of the resource
     */
    public static InputStream getResource(String path) {
        // @@author akgrenSoar-reused
        // Source: https://stackoverflow.com/a/15749281/6943913
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        return classLoader.getResourceAsStream(path);
    }

    /**
     * Run main logic when user presses enter
     */
    @FXML
    private void handleUserInput() {
        assert inputField != null;
        assert dukeData != null;
        assert outputStream != null;
        assert errorStream != null;
        assert taskView != null;

        // Execute Duke main logic
        displayInput(inputField.getText());
        DukeLogic.execute(dukeData, inputField.getText());
        displayOutput(outputStream.toString());
        displayError(errorStream.toString());

        // Force display to refresh taskList
        taskView.refresh();

        // Clear all Input/Output
        inputField.clear();
        outputStream.reset();
        errorStream.reset();

        assert inputField.getText().isBlank();
        assert outputStream.toString().isBlank();
        assert errorStream.toString().isBlank();
    }

    /**
     * Clear the display of all chat bubbles
     */
    @FXML
    private void clearDisplay() {
        assert dialogContainer != null;

        dialogContainer.getChildren().clear();
        displayOutput(WELCOME_MESSAGE);
    }

    /**
     * Show user input
     * @param userInput the user input
     */
    private void displayInput(String userInput) {
        assert dialogContainer != null;
        assert userInput != null;

        if (userInput.isBlank()) {
            return;
        }

        Pane inputBubble = ChatBubble.getUserDialog(userInput, userAvatar);
        dialogContainer.getChildren().add(inputBubble);
    }

    /**
     * Show output message
     * @param outputMessage the output message
     */
    private void displayOutput(String outputMessage) {
        assert dialogContainer != null;
        assert outputMessage != null;

        if (outputMessage.isBlank()) {
            return;
        }

        Pane outputBubble = ChatBubble.getDukeDialog(outputMessage, dukeAvatar);
        dialogContainer.getChildren().add(outputBubble);
    }

    /**
     * Show error message
     * @param errorMessage the error message
     */
    private void displayError(String errorMessage) {
        assert dialogContainer != null;
        assert errorMessage != null;

        if (errorMessage.isBlank()) {
            return;
        }

        Pane errorBubble = ChatBubble.getErrorDialog(errorMessage, errorAvatar);
        dialogContainer.getChildren().add(errorBubble);
    }

}
