package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents a command that will display all tasks in the task list.
 */
public class ListCommand extends Command {

    /**
     * Displays all the tasks in the task list.
     *
     * @param tasks the task list that is to be displayed.
     * @param ui the ui that displays the task list.
     * @param storage unused.
     */
    @Override
    public void executeCommand(TaskList tasks, Ui ui, Storage storage) {
        ui.showAllTasks(tasks);
    }

    /**
     * Indicates that this command is not an exit command.
     *
     * @return false since this command is not an exit command.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
