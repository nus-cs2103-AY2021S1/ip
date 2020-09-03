package blue.command;

import java.io.IOException;

import blue.Storage;
import blue.exception.SaveTaskFailedException;
import blue.task.Task;
import blue.task.Tasks;
import blue.ui.Ui;

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
     * Adds a task to the task list and returns a response consisting a message to indicate successful
     * addition.
     *
     * @param tasks   the task list.
     * @param ui      interacts with user.
     * @param storage loads and save tasks.
     * @return the response to task command.
     * @throws SaveTaskFailedException If the task cannot be saved.
     */
    @Override
    public CommandResponse execute(Tasks tasks, Ui ui, Storage storage) throws SaveTaskFailedException {
        tasks.addTask(this.task);
        try {
            storage.addTask(task);
        } catch (IOException ex) {
            throw new SaveTaskFailedException(tasks.getSize());
        }
        return new CommandResponse(ui.getAddTaskMessage(task, tasks.getSize()), this.isExit());
    }
}
