package duke.command;

import duke.Ui;
import duke.Storage;
import duke.tasks.TaskList;

/**
 * Command to print list of tasks
 */
public class ListCommand extends Command {
    public ListCommand() {
        super(false);
    }

    /**
     * Makes Ui print list of tasks
     *
     * @param tasks List of tasks
     * @param ui User interface to print task
     * @param storage File storage object
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printList(tasks);
    }
}
