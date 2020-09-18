package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.ui.Ui;

/**
 * Represents a Duke application.
 *
 * @author Tee Kok Siang
 */
public class Duke {
    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;

    /**
     * Constructs a Duke object.
     * Initializes {@link Ui}, {@link Storage} and {@link TaskList}.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage("data/tasks.txt");
        tasks = new TaskList(storage.load());
    }

    /**
     * Constructs a Duke object.
     * Initializes {@link Ui}, {@link Storage} and {@link TaskList}.
     *
     * @param filePath File path of file that stores task data.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
    }

    /**
     * Parses the input and executes the command based on the input.
     *
     * @param input Text input from the user.
     * @return Formatted message based on the executed command.
     */
    public String getResponse(String input) {
        assert input != null : "Input should not be null";
        try {
            Command c = Parser.parse(input);
            return c.execute(tasks, ui, storage);
        } catch (DukeException e) {
            return e.getLocalizedMessage();
        }
    }
}
