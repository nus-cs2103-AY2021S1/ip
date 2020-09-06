package duke.command;

import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.util.Storage;

/**
 * Class representing a delete task command.
 */
public class DeleteCommand extends Command {

    private final int taskIdx;

    /**
     * Creates a new {@code DeleteCommand}.
     * @param taskIdx Index of the task to be deleted.
     */
    public DeleteCommand(int taskIdx) {
        this.taskIdx = taskIdx;
    }

    /**
     * Deletes the task
     * @param tasks {@link TaskList} containing list of tasks.
     * @param ui {@link Ui} object.
     * @param storage {@link Storage} object.
     * @throws DukeException If the provided index is not associated with a task.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        assertArgumentsValid(tasks, ui, storage);
      
        if (taskIdx > tasks.size() || taskIdx <= 0) {
            throw new DukeException("No task with this ID!");
        }

        Task task = tasks.getTask(taskIdx);
        tasks.deleteTask(task);
        storage.saveData(tasks);
        ui.botOutput("Noted. I've removed this task:\n" + task);
    }
}
