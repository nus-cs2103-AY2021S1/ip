package duke.command;

import duke.Storage;
import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Encapsulates a command that will add a task.
 */
public class AddCommand extends Command {
    /**
     * The task to be added.
     */
    private final Task task;

    /**
     * Initialises a new instance.
     *
     * @param task The task to be added.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Executes the Add command by adding the task to the task list, saving the task to the
     * storage, and then printing a message indicating that the task was successfully added.
     *
     * @param tasks   The list of tasks known by the chat bot.
     * @param ui      The UI that is used by the chat bot.
     * @param storage The storage that is used by the chat bot.
     * @return A string detailing the outcome of the execution.
     * @throws DukeException If the execution fails at any step.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.addTask(task);

        storage.saveNewTask(task);

        return String
                .format("Got it. I've added this task:\n  %s\nNow you have %d tasks in the list.",
                        task, tasks.size());
    }
}
