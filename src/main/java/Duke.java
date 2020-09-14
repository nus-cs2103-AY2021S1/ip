import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ArrayList;

public class Duke  {
    private Ui ui;
    private TaskSaveAndLoadManager taskSaveAndLoadManager = new TaskSaveAndLoadManager();
    private TaskManager taskManager;

    /**
     * Creates a Duke object and initialises a Task Manager.
     *
     * @throws IOException when input or output operation fails.
     */
    public Duke() throws IOException {
        if (taskSaveAndLoadManager.loadTaskManager() != null) {
            this.taskManager = taskSaveAndLoadManager.loadTaskManager();
        } else {
            this.taskManager = new TaskManager(new ArrayList<>());
        }
    }

    /**
     * Initialises the UI.
     *
     * @param dialogContainer The vertical layout to contain dialogues.
     */
    public void setUi(VBox dialogContainer) {
        this.ui = new Ui(dialogContainer);
    }

    /** Greets the user.*/
    public void greet() {
        assert (ui != null) : "UI must not be null.";
        ui.greet();
    }

    /**
     * Returns whether the input command is bye.
     *
     * @param input String to represent user input.
     * @return a boolean indicating if the user input is a ByeCommand.
     * @throws IOException when input or output operation fails.
     */
    public boolean isByeCommand(String input) throws IOException {
        try {
            Command c = Parser.parse(input);
            c.execute(taskManager, ui);
            taskSaveAndLoadManager.saveTaskManager(this.taskManager);
            return c.isBye();
        } catch (DukeException e) {
            ui.print(e.getMessage());
        }
        return false;
    }
}
