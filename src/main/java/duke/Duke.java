package duke;

import java.nio.file.Path;
import java.nio.file.Paths;

import duke.command.Command;
import duke.exception.DukeException;
import duke.task.TaskList;


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

    /**
     * Runs the duke chat bot by displaying messages using ui and parsing inputs using parser.
     * Takes in command and executes it.
     * If command is an exit command, while loop breaks and chat bot stops running.
     */
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
