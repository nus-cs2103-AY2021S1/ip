package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.Task;
import duke.task.TaskList;

import java.util.List;

/**
 * Represents a command to display all the tasks in the user's list of tasks.
 */
public class ListCommand extends Command {

    /**
     * Returns a string containing all the user's tasks and the relevant task information.
     *
     * @param tasks List of tasks belonging to the user.
     * @param ui Ui object created for the Duke object to handle user interactions.
     * @param storage Storage object used by the Duke object for file operations.
     * @return String containing a list of all the user's tasks.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        List<Task> listOfTasks = tasks.getTaskList();
        return ui.showTaskList(listOfTasks);
    }

    /**
     * Indicates if the Duke session has ended.
     *
     * @return False since the Duke session has not been terminated.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
