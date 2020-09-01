package duckie.command;

import duckie.Storage;
import duckie.exception.DuckieException;
import duckie.exception.DuckieNoListException;
import duckie.task.Task;
import duckie.task.TaskList;
import duckie.ui.Ui;

/**
 * Command to list all the current tasks in the TaskList
 */
public class ListCommand extends Command {
    /**
     * List and display all the tasks in the TaskList currently
     * @param tasks TaskList containing all the tasks
     * @param ui Ui to interact with the users
     * @param storage Storage to write to File
     * @throws DuckieException
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DuckieException {
        if (tasks.getTaskList().size() == 0) {
            throw new DuckieNoListException();
        } else {
            int index = 1;
            String output = "Quack! You have these in your list currently: \n";
            for (Task task : tasks.getTaskList()) {
                output += index + ". " + task + "\n";
                index++;
            }
            return output;
        }
    }
}
