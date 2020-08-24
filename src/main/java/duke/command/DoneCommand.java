package duke.command;

import duke.*;

/**
 * Represents a command to mark a task as done.
 */
public class DoneCommand extends Command {
    /** The number of the task to be marked as done. */
    private int taskNumber;

    /**
     * Creates a new done command with the specified number of the task to mark as done.
     * @param taskNumber The number of the task to be marked as done.
     */
    public DoneCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Marks the task with the task number from the specified task list as done.
     * @param tasks The task list the command is executed with.
     * @param ui The ui the command is executed with.
     * @param storage The storage the command is executed with.
     * @throws DukeException If there was a problem with executing the command.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            tasks.markTaskAsDone(taskNumber);
            ui.printDoneTask(tasks.getTask(taskNumber));
            storage.save(tasks.getTasks());
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("☹ OOPS!!! The task number is not valid.");
        }
    }
}
