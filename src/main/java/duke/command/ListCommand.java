package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents a command that lists all tasks in the taskList.
 */
public class ListCommand extends Command {
    /**
     * Executes a list operation.
     *
     * @param taskList TaskList that the tasks are listed from.
     * @param ui Ui responsible for the operation.
     * @param storage Storage associated with the operation
     */
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return ui.printList(taskList);
    }
}
