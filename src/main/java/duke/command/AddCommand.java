package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

/**
 * Command that adds new tasks into the TaskList.
 */
public class AddCommand extends Command {

    /** Task to be added. */
    private Task task;

    /**
     * Creates a command to add task into TaskList.
     * @param task Task to be added.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Prints String returned by getExecuteString method.
     * @param tasks TaskList that is being executed on.
     * @param ui Ui to update the user.
     * @param storage Storage to update the text file.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printString(getExecuteString(tasks, ui, storage));
    }

    /**
     * Adds task into the TaskList, and prints results, after which the task
     * is added into the text file through Storage. Returns String produced by Ui.
     * @param tasks TaskList that the task is being added to.
     * @param ui Ui to update the user.
     * @param storage Storage to update the text file.
     * @return String of message of the command executed
     */
    @Override
    public String getExecuteString(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(task);
        storage.writeToFile(tasks.getTasks());
        return ui.getAddTaskString(task, tasks.size());
    }
}
