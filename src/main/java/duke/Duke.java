package duke;

import duke.ui.Ui;
import duke.command.Command;

/**
 * Represents a Duke executable program.
 * @author Tee Kok Siang
 */
public class Duke {
    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;

    public Duke() {
        ui = new Ui();
        storage = new Storage("data/tasks.txt");
        tasks = new TaskList(storage.load());
    }


    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
    }

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
