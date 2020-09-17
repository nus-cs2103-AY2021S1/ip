package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Class representing a command to mark a task as done.
 */
public class DoneCommand extends Command {

    private final int taskIdx;

    /**
     * Creates a new {@code DoneCommand}.
     *
     * @param taskIdx Index of the task to mark as done.
     */
    public DoneCommand(int taskIdx) {
        this.taskIdx = taskIdx;
    }

    /**
     * Marks a task as done.
     *
     * @param tasks {@link TaskList} containing list of tasks.
     * @param ui {@link Ui} object.
     * @param storage {@link Storage} object.
     * @throws DukeException If the TaskList is empty, of it the provided index is not associated with a task.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        assertArgumentsValid(tasks, ui, storage);

        if (tasks.size() == 0) {
            throw new DukeException("Your list is empty!");
        }

        if (taskIdx > tasks.size() || taskIdx <= 0) {
            throw new DukeException("No task with this ID!");
        }

        Task task = tasks.getTask(taskIdx);
        tasks.markAsDone(task);
        storage.saveData(tasks);
        ui.botOutput("Nice! I've marked this task as done:\n" + task);
    }
}
