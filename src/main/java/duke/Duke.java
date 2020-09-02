package duke;

import duke.command.Command;
import duke.exception.DukeException;

/**
 * duke is a chat bot cum task manager
 */

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public static final String FILE_PATH = "data/tasks.txt";

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(tasks, ui, storage);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
