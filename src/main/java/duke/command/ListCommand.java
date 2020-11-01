package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Is responsible for handing commands starting with <code>list</code>.
 */
public class ListCommand extends Command {
    public static final String COMMAND = "list";

    /**
     * Creates a <code>ListCommand</code> object
     */
    public ListCommand() {
        super();
    }

    /**
     * Passes all the tasks to the <code>Ui</code> to print them out.
     * @param tasks A list of tasks
     * @param ui An Ui object that correspond to interacting with the user
     * @param storage A database that stores the task list locally when the program is not running
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        assert tasks != null : "tasklist object cannot be null";
        assert ui != null : "ui object cannot be null";
        return ui.showTasks(tasks);
    }
}
