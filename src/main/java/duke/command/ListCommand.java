package duke.command;

import duke.Storage;
import duke.Ui;

import duke.task.TaskList;

/**
 * Represents a command to display all the tasks in the user's list of tasks.
 */
public class ListCommand extends Command {

    /**
     * Prints all the user's tasks and the relevant task information.
     *
     * @param tasks List of tasks belonging to the user.
     * @param ui Ui object created for the Duke object.
     * @param storage Storage object used by the Duke object for file operations.
     * @return
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.printTasks(tasks.getTaskList());
    }

    /**
     * Indicates if the DukeBot session has ended.
     *
     * @return False since the DukeBot session has not been terminated.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
