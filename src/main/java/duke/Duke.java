package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.ui.Ui;
import duke.util.Parser;
import duke.util.Storage;
import duke.util.TaskList;

/**
 * Represents the main class for the Duke application.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs an instance of Duke.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage("tasks.txt");
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Returns Duke's response to a command after executing it.
     *
     * @param input User command.
     * @return Duke's response to the command.
     * @throws DukeException if the input does not match any commands.
     */
    public String getResponse(String input) throws DukeException {
        String response;
        try {
            Command command = Parser.parse(input);
            response = command.execute(tasks, ui, storage);
        } catch (DukeException e) {
            throw e;
        }

        return response;
    }
}
