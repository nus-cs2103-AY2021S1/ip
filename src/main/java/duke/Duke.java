package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Class representing the Duke chatbot.
 */
public class Duke {
    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;

    /**
     * Initialises a new {@code Duke} chatbot.
     */
    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage();
        this.tasks = new TaskList();

        try {
            tasks.loadFromStorage(storage);
        } catch (DukeException e) {
            ui.showError(e);
        }
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
