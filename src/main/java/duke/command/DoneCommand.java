package duke.command;

import duke.*;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Class representing a command to mark a task as done.
 */
public class DoneCommand extends Command {
    
    private final int taskIdx;

    /**
     * Creates a new DoneCommand.
     * @param taskIdx Index of the task to mark as done.
     * @throws DukeException If no task index is provided, or if the format provided is invalid.
     */
    public DoneCommand(String taskIdx) throws DukeException {
        try {
            this.taskIdx = Integer.parseInt(taskIdx);
        } catch (NumberFormatException e) {
            throw new DukeException("Invalid arguments provided!");
        }
    }

    /**
     * Marks a task as done.
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
        tasks.markAsDone(task);
        storage.doneTask(tasks.getTask(taskIdx));
        ui.botOutput("Nice! I've marked this task as done:\n" + task);
    }
}
