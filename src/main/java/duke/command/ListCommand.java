package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents a command which lists the task.
 */
public class ListCommand extends Command {

    /**
     * Executes the operation for the list of task to be shown.
     *
     * @param tasks TaskList linked to the program.
     * @param ui Ui linked to the program.
     * @param storage Storage linked to the program.
     * @return String that contains the executed ListCommand.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showTaskList(tasks.getTasks());
    }

    /**
     * Returns a boolean that dictates if the program is running.
     *
     * @return False.
     */
    @Override
    public boolean isExit() {
        return false;
    }

}
