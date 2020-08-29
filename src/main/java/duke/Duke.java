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
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            c.execute(tasks, ui, storage);
            return ui.getNextMessage();
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
