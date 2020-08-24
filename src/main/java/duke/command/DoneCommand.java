package duke.command;

import java.io.IOException;

import duke.Storage;
import duke.Ui;
import duke.exception.InvalidTaskNumberException;
import duke.exception.SaveTaskFailedException;
import duke.task.Tasks;

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
     * Mark the task as done and print a message to indicate successful mark as done.
     *
     * @param tasks   the task list.
     * @param ui      interacts with user.
     * @param storage loads and save tasks.
     * @throws InvalidTaskNumberException If the task number is invalid.
     * @throws SaveTaskFailedException    If the task list cannot be saved.
     */
    public void execute(Tasks tasks, Ui ui, Storage storage)
            throws InvalidTaskNumberException, SaveTaskFailedException {
        try {
            tasks.getTask(this.taskIndex).markAsDone();
            storage.updateTasks(tasks);
            ui.printMarkTaskAsDone(tasks.getTask(this.taskIndex));
        } catch (IndexOutOfBoundsException ex) {
            throw new InvalidTaskNumberException("The task to be marked as done does not exist!");
        } catch (IOException ex) {
            throw new SaveTaskFailedException(this.taskIndex);
        }
    }
}
