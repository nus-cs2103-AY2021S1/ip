package duckie.command;

import duckie.Storage;
import duckie.exception.DuckieException;
import duckie.task.Task;
import duckie.task.TaskList;
import duckie.ui.Ui;

/**
 * Command to add task into the TaskList.
 */
public class AddCommand extends Command {
    private Task task;

    /**
     * Instantiates the AddCommand.
     *
     * @param task Task to be added.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Executes the command to add task into TaskList.
     *
     * @param tasks TaskList containing all the tasks.
     * @param ui Ui to interact with the users.
     * @param storage Storage to write to File.
     * @return output Display added task and the number of tasks in the list currently.
     * @throws DuckieException Catch any File Error exception.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DuckieException {
        assert task != null;
        tasks.addTask(this.task);
        try {
            storage.saveToFile(tasks.getTaskList());
        } catch (DuckieException e) {
            throw e;
        }
        String output = "Quack! Added: \n";
        output += task + "\n";
        output += "Now you have " + tasks.getTaskList().size() + " task(s) in the list.";
        return output;
    }
}
