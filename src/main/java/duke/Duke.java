package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.util.Parser;
import duke.util.Storage;

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
