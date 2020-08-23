package duke.command;

import duke.Storage;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Encapsulates a command that will delete a task.
 */
public class DeleteCommand extends Command {
    /**
     * The ID of the task to be deleted.
     */
    private final int taskId;

    /**
     * Initialises a new instance.
     *
     * @param taskId The ID of the task to be deleted.
     */
    public DeleteCommand(int taskId) {
        this.taskId = taskId;
    }

    /**
     * Executes the Delete command by deleting the task from the task list, updating the storage,
     * and then printing a message indicating that the task was successfully deleted.
     *
     * @param tasks   The list of tasks known by the chat bot.
     * @param ui      The UI that is used by the chat bot.
     * @param storage The storage that is used by the chat bot.
     * @throws DukeException If the execution fails at any step.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task = tasks.deleteTask(taskId);

        storage.deleteExistingTask(taskId);

        ui.print(String.format(
                "Noted. I've removed this task:\n%s\nNow you have %d tasks in the list.", task,
                tasks.size()));
    }
}
