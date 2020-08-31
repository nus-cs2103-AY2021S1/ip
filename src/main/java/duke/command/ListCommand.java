package duke.command;

import duke.Storage;
import duke.Ui;
import duke.tasks.TaskList;

/**
 * Command to print list of tasks
 */
public class ListCommand extends Command {
    public ListCommand() {
        super(false);
    }

    /**
     * Returns string containing list of tasks
     *
     * @param tasks List of tasks
     * @param ui User interface to print task
     * @param storage File storage object
     * @return List of tasks in string format
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.printList(tasks);
    }
}
