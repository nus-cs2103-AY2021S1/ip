package duke;

import duke.exceptions.DukeException;
import duke.logic.Parser;
import duke.logic.TaskList;
import duke.logic.commands.Command;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Represents Duke program that executes the various commands given by the user.
 * A Duke object consists of a Storage object, which stores the tasks in the computer,
 * a TaskList object, which keeps track of the tasks, and a Ui object, which replies
 * the user accordingly.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke() {
        ui = new Ui();
    }

    /**
     * Instantiates a Duke object and loads the tasks stored in the computer, if any.
     *
     * @param filePath The filePath of the file which stores the data.
     */
    public Duke(String filePath) {
        ui = new Ui();

        try {
            storage = new Storage(filePath);
            tasks = new TaskList(storage.load());
        } catch (DukeException ex) {
            ui.showError(ex.getMessage());
            tasks = new TaskList();
        }
    }

    public String getResponse(String input) {
        boolean isExit = false;
        while (!isExit) {
            try {
                Command c = Parser.parse(input);
                isExit = c.isExit();
                return c.execute(tasks, ui, storage);
            } catch (DukeException ex) {
                return ui.showError(ex.getMessage());
            }
        }

        assert false : "Response to user input is supposed to be returned";
        return "";
    }
}
