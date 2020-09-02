package nite.command;

import nite.storage.Storage;
import nite.task.TaskList;
import nite.ui.Ui;

/**
 * Represents a ListCommand where user wants to list all the tasks.
 */
public class ListCommand extends Command {

    /**
     * Creates a ListCommand.
     */
    public ListCommand() {
    }

    /**
     * Executes the command to list and display tasks in tasklist.
     *
     * @param tasks Tasklist containing current tasks.
     * @param ui Ui for displaying output.
     * @param storage Storage of tasks in hard disk.
     * @return String displaying completion of Command.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String tasksString = tasks.listTasks();
        return ui.showAction("  Here are the tasks in your list:\n" + tasksString);
    }
}
