package duke.command;

import duke.Storage;
import duke.exception.DukeException;
import duke.exception.InvalidTaskIdException;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Encapsulates a command that will update a task.
 */
public class UpdateCommand extends Command {
    /**
     * The ID of the task to be updated.
     */
    private final int taskId;

    /**
     * The updated task.
     */
    private final Task updatedTask;

    /**
     * Initialises a new instance.
     *
     * @param taskId      The ID of the task to be updated.
     * @param updatedTask The updated task.
     */
    public UpdateCommand(int taskId, Task updatedTask) {
        this.taskId = taskId;
        this.updatedTask = updatedTask;
    }

    /**
     * Executes the Update command by updating the task from the task list, updating the storage,
     * and then printing a message showing the new updated task list.
     *
     * @param tasks   The list of tasks known by the chat bot.
     * @param storage The storage that is used by the chat bot.
     * @return A string detailing the outcome of the execution.
     * @throws DukeException If the execution fails at any step.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        if (taskId > tasks.size()) {
            throw new InvalidTaskIdException("No task with the given ID was found!");
        }

        tasks.updateTask(taskId, updatedTask);
        storage.updateExistingTask(taskId, updatedTask);

        return String.format("Noted. I've updated the task.\n\nHere's your updated task "
                + "list:\n%s\n", tasks);
    }
}
