package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.task.TaskList;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Personal chat bot to keep track of user's tasks.
 */
public class Duke {
    /**
     * The file path of where the data is stored.
     */
    private static final Path filePath = Paths.get(".", "data", "duke.txt");

    private final Storage storage;
    private final Ui ui;
    private TaskList tasks;

    /**
     * Initializes a new Duke Object.
     * Tries to load existing tasks from an existing data file.
     * If that fails, a new task list instance is made.
     */
    public Duke() {
        this.storage = new Storage(Duke.filePath);
        this.ui = new Ui();

        try {
            this.tasks = new TaskList(this.storage.getTasks());
        } catch (DukeException e) {
            ui.showLoadingError();
            this.tasks = new TaskList();
        }
    }

    /**
     * Runs Duke, waits for the user to interact with it and responds to the user.
     * <p>
     * Supports the commands:
     * bye, done, delete, list, find, deadline, event, todo
     * </p>
     *
     * @param args user input.
     */
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }
        }
    }
}
