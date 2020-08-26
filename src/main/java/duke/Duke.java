package duke;

import duke.command.Command;
import duke.task.TaskList;

/**
 * Represents a functional chat-bot that helps to keep track of todo, deadline and event tasks.
 */
public class Duke {

    /** Handles any read/write requests. */
    private Storage storage;

    /** Container for the storage of tasks. */
    private TaskList tasks;

    /** Handles all User Interaction elements of the chat-bot. */
    private Ui ui;

    /**
     * Loads tasks into a task list from a local file that is read in based on an user-specified filepath.
     *
     * @param filePath The filepath to find the local file.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = storage.load();
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Takes in user input, parses the input and executes the correct command to process the tasks.
     * Exits only when an ExitCommand is generated based on user input.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = new Parser(fullCommand).parse();
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }
        }

    }

    /**
     * Represents the entry point for the application.
     *
     * @param args An array of command-line arguments for the application
     * @throws DukeException If there are any errors with the program execution.
     */
    public static void main(String[] args) throws DukeException {
        new Duke("data/tasks.txt").run();
    }
}
