package duckie.command;

import duckie.task.Task;
import duckie.task.TaskList;
import duckie.exception.DuckieException;
import duckie.Ui;
import duckie.Storage;

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
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DuckieException {
        tasks.addTask(this.task);
        try {
            storage.saveToFile(tasks.getTaskList());
        } catch (DuckieException e) {
            throw e;
        }
        Ui.addTaskReply(this.task, tasks.getTaskList());
    }
}
