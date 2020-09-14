package duke.gui;

import java.io.IOException;

import duke.Duke;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class DukeGuiWindow extends BorderPane {

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

        duke.getGuiResponse().greet();
        dukeMessage.setText(duke.getGuiResponse().getResponse());
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
    public void exit() throws IOException {
        ExitWindow exitWindow = new ExitWindow();
        boolean saveTasks = exitWindow.display();

        if (saveTasks) {
            saveTasks();
        }

        stage.close();
    }
}
