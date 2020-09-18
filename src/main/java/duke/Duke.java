package duke;

import duke.command.Command;
import duke.command.CommandResponse;

/**
 * Represents a personalized chat bot where a user can keep track of different tasks.
 */
public class Duke {
    /** The storage to store and load all the tasks. */
    private final Storage storage;
    /** Represents the list of different tasks. */
    private TaskList tasks;

    /**
     * Creates a new Duke chat bot that loads and saves tasks in the filepath.
     *
     * @param filePath is the path in which tasks are loaded and saved in.
     */
    public Duke(String filePath) {
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            Ui.showError(e);
            tasks = new TaskList();
        }
    }

    /**
     * Gives a command response to user input.
     *
     * @param input The input from the user.
     * @return the command response to the user.
     */
    public CommandResponse getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(tasks, storage);
        } catch (DukeException e) {
            return new CommandResponse(Ui.showError(e), false);
        }
    }
}
