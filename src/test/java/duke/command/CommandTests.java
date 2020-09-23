package duke.command;

import java.util.ArrayList;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Initializes the storage, tasklist and ui for the children test classes.
 */
public class CommandTests {
    protected final Storage storage = new Storage();
    protected final TaskList taskList = new TaskList(new ArrayList<>());
    protected final Ui ui = new Ui();

    public String executeTask(Command command) throws DukeException {
        return command.execute(taskList, ui, storage);
    }
}
