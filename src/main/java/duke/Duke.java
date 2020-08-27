package duke;

import duke.command.Command;
import duke.component.DukeException;
import duke.component.Parser;
import duke.component.Storage;
import duke.component.Ui;
import duke.task.TaskList;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Duke {
    private static final Path filepath = Paths.get(".", "data", "duke.txt");
    private Storage storage;
    private TaskList taskList;
    private final Ui ui;

    /**
     * Creates an instance of Duke, instance of ui created too. TaskList initialised
     * with empty list of tasks if file lookup is unsuccessful.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage(filepath);
        try {
            taskList = new TaskList(storage.getListOfTasks());
        } catch (DukeException e) {
            System.out.println("Error in extracting tasks from saved file");
            taskList = new TaskList();
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }

    /**
     * method to execute the Duke logic
     */
    public void run() {
        ui.showWelcome();
        ui.showLine();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(taskList, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }
}
