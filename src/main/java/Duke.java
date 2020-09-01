import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ArrayList;

public class Duke  {

    private Ui ui;
    private TaskSaveAndLoadManager taskSaveAndLoadManager = new TaskSaveAndLoadManager();
    private TaskManager taskManager;

    public Duke() throws IOException {
        if (taskSaveAndLoadManager.loadTaskManager() != null) {
            this.taskManager = taskSaveAndLoadManager.loadTaskManager();
        } else {
            this.taskManager = new TaskManager(new ArrayList<>());
        }
    }

    public void setUi(VBox dialogContainer) {
        this.ui = new Ui(dialogContainer);
    }

    public boolean getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            c.execute(taskManager, ui);
            return c.isBye();
        } catch (DukeException e) {
            ui.print(e.getMessage());
        }
        return false;
    }

    public void greet() {
        ui.greet();
    }
}
