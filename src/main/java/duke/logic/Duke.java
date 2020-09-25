package duke.logic;

import duke.command.Command;
import duke.exception.DukeException;

/**
 * Represents the Duke bot, which has a task list, a storage space and a user interface.
 */
public class Duke {

    /** Storage space to save and load task list */
    private Storage storage;

    /** List of tasks saved by Duke */
    private Tasklist tasks;

    /**
     * Creates a Duke object with a default filepath.
     */
    public Duke() {
        storage = new Storage("data/tasks.txt");
        try {
            tasks = storage.load();
        } catch (DukeException dukeException) {
            tasks = new Tasklist();
        }
    }

    /**
     * Creates a Duke object with the given filepath.
     *
     * @param filepath The path of the file where the
     *                 list of tasks is stored in a .txt file.
     */
    public Duke(String filepath) {
        storage = new Storage(filepath);
        try {
            tasks = storage.load();
        } catch (DukeException dukeException) {
            tasks = new Tasklist();
        }
    }

    public String getResponse(String input) {
        try {
            Command command = Parser.parseCommand(input);
            return command.execute(tasks, storage);
        } catch (DukeException dukeException) {
            return dukeException.getMessage();
        }
    }
}
