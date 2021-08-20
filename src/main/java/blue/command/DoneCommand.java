package blue.command;

import java.io.IOException;

import blue.Storage;
import blue.exception.InvalidTaskNumberException;
import blue.exception.SaveTaskFailedException;
import blue.task.Tasks;
import blue.ui.Ui;

/**
 * The Done command marks a task as done.
 */
public class DoneCommand extends Command {
    /**
     * The Task index of the task to be marked as done.
     */
    private final int taskIndex;

    /**
     * Instantiates a new Done command.
     *
     * @param taskIndex the task index.
     */
    public DoneCommand(int taskIndex) {
        this.commandType = CommandType.DONE;
        this.taskIndex = taskIndex;
    }

    /**
     * Mark the task as done and returns a response consisting a message to indicate successful mark as done.
     *
     * @param tasks   the task list.
     * @param ui      interacts with user.
     * @param storage loads and save tasks.
     * @return the response to done command.
     * @throws InvalidTaskNumberException If the task number is invalid.
     * @throws SaveTaskFailedException    If the task list cannot be saved.
     */
    @Override
    public CommandResponse execute(Tasks tasks, Ui ui, Storage storage)
            throws InvalidTaskNumberException, SaveTaskFailedException {
        try {
            tasks.getTask(this.taskIndex).markAsDone();
            storage.updateTasks(tasks);
            return new CommandResponse(ui.getMarkTaskAsDoneMessage(tasks.getTask(this.taskIndex)),
                    this.isExit());
        } catch (IndexOutOfBoundsException ex) {
            throw new InvalidTaskNumberException("The task to be marked as done does not exist!");
        } catch (IOException ex) {
            throw new SaveTaskFailedException(this.taskIndex);
        }
    }
}
