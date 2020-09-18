package duke;

import duke.command.Command;
import duke.command.CommandResponse;

/**
 * Represents a personal assistant chat bot that helps a person to keep track of various tasks.
 */
public class Duke {
    /** The storage to handle saving and loading tasks. */
    private Storage storage;
    /** The list of tasks. */
    private TaskList tasks;

    /**
     * Creates a new chat bot that saves and loads tasks from the given filepath.
     *
     * @param filePath The file path to load tasks from and save tasks to.
     */
    public Duke(String filePath) {
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            Ui.respondError(e);
            tasks = new TaskList();
        }
    }

    /**
     * Generates a response to user input.
     *
     * @param input The string input from the user.
     * @return The command response to the user input.
     */
    public CommandResponse getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(tasks, storage);
        } catch (DukeException e) {
            return new CommandResponse(Ui.respondError(e));
        }
    }
}
