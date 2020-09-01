package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

/**
 * Represents a command to delete a task.
 */
public class DeleteCommand extends Command {
    /** The number of the task to be deleted. */
    private int taskNumber;

    /**
     * Creates a new delete command with the specified number of the task to be deleted.
     * @param taskNumber The number of the task to be deleted.
     */
    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Deletes the task with the task number from the specified task list.
     * @param tasks The task list the command is executed with.
     * @param storage The storage the command is executed with.
     * @return A command response that represents the result of completing a delete command.
     * @throws DukeException If there was a problem with executing the command.
     */
    @Override
    public CommandResponse execute(TaskList tasks, Storage storage) throws DukeException {
        try {
            Task removedTask = tasks.deleteTask(taskNumber);
            storage.save(tasks.getTasks());
            return new CommandResponse(Ui.respondDeleteTask(removedTask, tasks), this.isExit());
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("☹ OOPS!!! The task number is not valid.");
        }
    }
}
