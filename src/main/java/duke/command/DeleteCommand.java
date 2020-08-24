package duke.command;

import duke.Storage;
import duke.Ui;
import duke.exception.InvalidTaskNumberException;
import duke.exception.SaveTaskFailedException;
import duke.task.Task;
import duke.task.Tasks;

import java.io.IOException;

/**
 * The Delete command which deletes a task.
 */
public class DeleteCommand extends Command {
    /**
     * The Command type.
     */
    private final CommandType commandType;
    /**
     * The Task index of task to be deleted.
     */
    private final int taskIndex;

    /**
     * Instantiates a new Delete command.
     *
     * @param taskIndex the task index.
     */
    public DeleteCommand(int taskIndex) {
        this.commandType = CommandType.DELETE;
        this.taskIndex = taskIndex;
    }

    /**
     * Deletes the task and print a message to indicate successful deletion.
     *
     * @param tasks   the task list.
     * @param ui      interacts with user.
     * @param storage loads and save tasks.
     * @throws InvalidTaskNumberException If the task number is invalid.
     * @throws SaveTaskFailedException    If the task list cannot be saved.
     */
    public void execute(Tasks tasks, Ui ui, Storage storage) throws InvalidTaskNumberException, SaveTaskFailedException {
        try {
            Task task = tasks.getTask(taskIndex);
            tasks.removeTask(taskIndex);
            storage.updateTasks(tasks);
            ui.printDeleteTask(task, tasks.getSize());
        } catch (IndexOutOfBoundsException ex) {
            throw new InvalidTaskNumberException("The task to be deleted does not exist!");
        } catch (IOException ex) {
            throw new SaveTaskFailedException(taskIndex);
        }
    }
}
