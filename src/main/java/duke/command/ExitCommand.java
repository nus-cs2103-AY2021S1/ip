package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Is responsible for handing commands starting with <code>bye</code>.
 */
public class ExitCommand extends Command {
    public static final String COMMAND = "bye";

    /**
     * Creates an <code>ExitCommand</code> object.
     */
    public ExitCommand() {
    }

    /**
     * Stops the program.
     * @return An indicator that helps the program know when to stop
     */
    @Override
    public boolean isExit() {
        return true;
    }

    /**
     * Copies tasks from temporary TaskList to local storage, then say goodbye.
     * @param tasks A list of tasks
     * @param ui An Ui object that correspond to interacting with the user
     * @param storage A database that stores the task list locally when the program is not running
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        assert tasks != null : "tasklist object cannot be null";
        assert ui != null : "ui object cannot be null";
        assert storage != null : "storage object cannot be null";
        storage.write(tasks);
        return ui.showGoodbye();
    }
}
