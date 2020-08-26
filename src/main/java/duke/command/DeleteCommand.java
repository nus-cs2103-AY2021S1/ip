package duke.command;

import duke.*;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Class representing a delete task command.
 */
public class DeleteCommand extends Command {
    
    private final int taskIdx;

    /**
     * Creates a new DeleteCommand.
     * @param taskIdx Index of the task to be deleted.
     * @throws DukeException If no task index is provided, or if the format provided is invalid.
     */
    public DeleteCommand(String taskIdx) throws DukeException {
        try {
            this.taskIdx = Integer.parseInt(taskIdx);
        } catch (NumberFormatException e) {
            throw new DukeException("Invalid arguments provided!");
        }
    }

    /**
     * Deletes the task
     * @param tasks List of tasks.
     * @param ui Ui object.
     * @param storage Storage object.
     * @throws DukeException If the provided index is not associated with a task.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (taskIdx > tasks.size()) {
            throw new DukeException("No task with this ID!");
        }
        
        Task task = tasks.getTask(taskIdx);
        tasks.deleteTask(task);
        storage.deleteTask(task);
        ui.botOutput("Noted. I've removed this task:\n" + task);
    }
}
