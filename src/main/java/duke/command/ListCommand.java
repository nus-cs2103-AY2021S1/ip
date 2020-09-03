package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a command to print all the tasks in the list.
 */
public class ListCommand extends Command {

    /**
     * Lists all the tasks in the taskList.
     * @param tasks is the list of tasks stored by Duke.
     * @param ui is the user interface to read inputs from the user and print messages.
     * @param storage deals with saving tasks into the file and loading tasks
     *                from the file.
     *
     * @return A string listing all the tasks.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.listText(tasks);
    }

    /**
     * Indicates Duke should keep running after this command is executed.
     * @return true.
     */
    @Override
    public boolean continueRunning() {
        return true;
    }
}
