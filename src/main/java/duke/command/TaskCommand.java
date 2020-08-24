package duke.command;

import java.io.IOException;

import duke.Storage;
import duke.Ui;
import duke.exception.SaveTaskFailedException;
import duke.task.Task;
import duke.task.Tasks;

/**
 * The Task command adds a task.
 */
public class TaskCommand extends Command {
    /**
     * The Task to be added.
     */
    private final Task task;

    /**
     * Instantiates a new Task command.
     *
     * @param task the task.
     */
    public TaskCommand(Task task) {
        this.commandType = CommandType.TASK;
        this.task = task;
    }

    /**
     * Adds a task to the task list and print a message to indicate successful addition.
     *
     * @param tasks   the task list.
     * @param ui      interacts with user.
     * @param storage loads and save tasks.
     * @throws SaveTaskFailedException If the task cannot be saved.
     */
    public void execute(Tasks tasks, Ui ui, Storage storage) throws SaveTaskFailedException {
        tasks.addTask(this.task);
        try {
            storage.addTask(task);
        } catch (IOException ex) {
            throw new SaveTaskFailedException(tasks.getSize());
        }
        ui.printAddTask(task, tasks.getSize());
    }
}
