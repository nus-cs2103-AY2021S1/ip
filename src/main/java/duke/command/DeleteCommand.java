package duke.command;

import java.io.IOException;

import duke.Storage;
import duke.exception.InvalidTaskNumberException;
import duke.exception.SaveTaskFailedException;
import duke.task.Task;
import duke.task.Tasks;
import duke.ui.Ui;

/**
 * The Delete command which deletes a task.
 */
public class DeleteCommand extends Command {
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
     * Deletes the task and returns a response consisting a message to indicate successful deletion.
     *
     * @param tasks   the task list.
     * @param ui      interacts with user.
     * @param storage loads and save tasks.
     * @return the response to delete command.
     * @throws InvalidTaskNumberException If the task number is invalid.
     * @throws SaveTaskFailedException    If the task list cannot be saved.
     */
    @Override
    public CommandResponse execute(Tasks tasks, Ui ui, Storage storage)
            throws InvalidTaskNumberException, SaveTaskFailedException {
        try {
            Task task = tasks.getTask(taskIndex);
            tasks.removeTask(taskIndex);
            storage.updateTasks(tasks);
            return new CommandResponse(ui.getDeleteTaskMessage(task, tasks.getSize()), this.isExit());
        } catch (IndexOutOfBoundsException ex) {
            throw new InvalidTaskNumberException("The task to be deleted does not exist!");
        } catch (IOException ex) {
            throw new SaveTaskFailedException(taskIndex);
        }
    }
}
