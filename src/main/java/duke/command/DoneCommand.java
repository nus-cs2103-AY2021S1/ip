package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents a command to mark a task as done.
 */
public class DoneCommand extends Command {
    /** The number of the task to be marked as done. */
    private final int taskNumber;

    /**
     * Creates a new done command with the specified number of the task to mark as done.
     *
     * @param taskNumber The number of the task to be marked as done.
     */
    public DoneCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Marks the task with the task number from the specified task list as done.
     *
     * @param tasks The task list the command is executed with.
     * @param storage The storage the command is executed with.
     * @return A command response that represents the result of completing a command to mark the task as done.
     * @throws DukeException If there was a problem with executing the command.
     */
    @Override
    public CommandResponse execute(TaskList tasks, Storage storage) throws DukeException {
        try {
            assert tasks != null && storage != null : "tasks and storage cannot be null.";
            tasks.markTaskAsDone(taskNumber);
            storage.save(tasks.getTasks());
            return new CommandResponse(Ui.respondDoneTask(tasks.getTask(taskNumber)));
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("OOPS!!! The task number is not valid.");
        }
    }
}
