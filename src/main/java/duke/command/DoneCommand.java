package duke.command;

import duke.exception.DukeException;
import duke.exception.ErrorMessage;
import duke.logic.Tasklist;
import duke.logic.Storage;
import duke.task.Task;

/**
 * Represents a Command to mark a Task in the task list as done.
 */
public class DoneCommand extends Command {

    /** The index of the task to be marked as done */
    private int taskNumber;

    /**
     * Creates a Command to mark a Task as done.
     *
     * @param taskNumber The index of the task to be marked as done.
     */
    public DoneCommand(int taskNumber) {
        super();
        this.taskNumber = taskNumber;
    }

    /**
     * Marks the task with the task number as done, saves the task list
     * and displays a message indicating completion of the command.
     *
     * @param tasks The task list.
     * @param storage The Storage object that saves the task list.
     * @return A String that indicates that the task has been marked as done.
     * @throws DukeException If task number entered is invalid.
     */
    @Override
    public String execute(Tasklist tasks, Storage storage) throws DukeException {
        try {
            Task completedTask = tasks.get(taskNumber);
            completedTask.markAsDone();
            storage.save(tasks);
            return "Nice! I've marked this task as done:\n" + "  "
                    + completedTask.toDisplayString();
        } catch (IndexOutOfBoundsException exception) {
            assert(taskNumber <= 0 || taskNumber > tasks.size());
            throw new DukeException(ErrorMessage.INVALID_TASK_NUMBER.getMessage());
        }
    }

    /**
     * Returns false as it is not an ExitCommand.
     *
     * @return False.
     */
    @Override
    public boolean isExit() {
        return false;
    }

}
