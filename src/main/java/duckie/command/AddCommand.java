package duckie.command;

import duckie.Storage;
import duckie.exception.DuckieException;
import duckie.task.Task;
import duckie.task.TaskList;
import duckie.ui.Ui;

/**
 * Command to add task into the TaskList
 */
public class AddCommand extends Command {
    private Task task;

    /**
     * Instantiate the AddCommand
     * @param task Task to be added
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Execute the command to add task into TaskList
     * @param tasks TaskList containing all the tasks
     * @param ui Ui to interact with the users
     * @param storage Storage to write to File
     * @throws DuckieException
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DuckieException {
        tasks.addTask(this.task);
        try {
            storage.saveToFile(tasks.getTaskList());
        } catch (DuckieException e) {
            throw e;
        }
        String output = "Quack! Added: \n";
        output += "Now you have " + tasks.getTaskList().size() + " task(s) in the list.";
        return output;
    }
}
