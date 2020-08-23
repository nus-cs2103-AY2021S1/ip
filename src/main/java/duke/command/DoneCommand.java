package duke.command;

import duke.Storage;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Encapsulates a command that will mark a task as complete.
 */
public class DoneCommand extends Command {
    /**
     * The ID of the task to be marked as complete.
     */
    private final int taskId;

    /**
     * Initialises a new instance.
     *
     * @param taskId The ID of the task to be marked as complete.
     */
    public DoneCommand(int taskId) {
        this.taskId = taskId;
    }

    /**
     * Executes the Done command by marking the task in the task list as complete, updating the
     * storage, and then printing a message to indicate that the task was marked as done.
     *
     * @param tasks   The list of tasks known by the chat bot.
     * @param ui      The UI that is used by the chat bot.
     * @param storage The storage that is used by the chat bot.
     * @throws DukeException If the execution fails at any step.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task = tasks.getTask(taskId);
        task.markAsDone();

        storage.updateExistingTask(taskId, task);

        ui.print(String.format("Nice! I've marked this task as done:\n%s", task));
    }
}
