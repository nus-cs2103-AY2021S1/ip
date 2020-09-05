package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.util.Parser;
import duke.util.Storage;

/**
 * Main class of the Duke bot.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Initialises a new Duke bot instance.
     */
    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage();
        this.tasks = new TaskList();

        tasks.loadFromStorage(storage);
    }

    /**
     * Function that takes in the user's input, processes and then executes it. It then sends a message back
     * to the user, based on the command given.
     *
     * @param input User input.
     * @return Message after the command has been processed and executed.
     */
    public String getResponse(String input) {
        assert input != null : "Input is null";

        try {
            Command c = Parser.parse(input);
            c.execute(tasks, ui, storage);
            return ui.getNextMessage();
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
