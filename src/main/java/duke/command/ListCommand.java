package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents the list command when user wants to list all task in TaskList.
 */
public class ListCommand extends Command {
    
    /**
     * Renders view of all tasks in task list.
     *
     * @param taskItems represents the tasks which is added to the task list.
     * @param ui        ui component which user interacts with or sees.
     * @param storage   Object for saving and loading tasks list to hard disk.
     * @throws duke.DukeException if task number specified by user does not exist.
     */
    @Override
    public void execute(TaskList taskItems, Ui ui, Storage storage) throws DukeException {
        ui.printReply(ui.formatReply(taskItems.toString()));
    }

    /**
     * Returns instruction to Duke class whether to terminate program.
     *
     * @return bool value to not terminate the program.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
