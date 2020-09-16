package duke.gui;

import java.io.IOException;

import duke.Duke;
import duke.version.Version;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class DukeGuiWindow extends BorderPane {

    @FXML
    private Label version;
    @FXML
    private TextField userInput;
    @FXML
    private Label dukeMessage;
    @FXML
    private Label userMessage;
    @FXML
    private Label messageLabel;

    private Duke duke;
    private Stage stage;

    /**
     * Sets up things on the start.
     * @param duke
     * @param stage
     */
    public void setUp(Duke duke, Stage stage) {
        this.duke = duke;
        this.stage = stage;

        // sets up style
        stage.getScene().getStylesheets().add("view/DarkTheme.css");

        // handles close request
        stage.setOnCloseRequest(event -> {
            event.consume();
            try {
                exit();
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        });

        // makes Duke greet the user
        duke.getGuiResponse().greet();
        dukeMessage.setText(duke.getGuiResponse().getResponse());

        // version
        version.setText(Version.CURRENT_VERSION);
    }

    /**
     * Handles the user input.
     * @throws IOException
     */
    @FXML
    public void handleUserInput() throws IOException {
        String input = userInput.getText();
        userMessage.setText(input);
        userInput.clear();

        String response = duke.getResponse(input);
        dukeMessage.setText(response);

        if (duke.getState().getExitLoop()) {
            duke.onExit();
            stage.close();
        }
    }

    /**
     * Saves all current tasks.
     * @throws IOException
     */
    @FXML
    public void saveTasks() throws IOException {
        duke.getStorage().saveCurrentTasks();
        messageLabel.setText(GuiResponse.TASK_SAVED);
    }

    /**
     * Asks the user if he or she wants to save current tasks, then exit the program.
     */
    @FXML
    public void exit() throws IOException {
        boolean saveTasks = ExitWindow.display();

        if (saveTasks) {
            saveTasks();
        }

        if (ExitWindow.isStillExit()) {
            stage.close();
        }
    }

    /**
     * Shows all available commands to the user.
     */
    @FXML
    public void showAllCommands() {
        AllCommandsWindow.display(duke);
    }

    /**
     * Shows tasks table
     */
    public void showAllTasks() {
        TaskListWindow.display(duke);
    }
}
