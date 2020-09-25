package duke.command;

import duke.exception.DukeException;
import duke.exception.ErrorMessage;
import duke.logic.Tasklist;
import duke.logic.Storage;
import duke.task.Task;

import java.time.DateTimeException;

/**
 * Represents a Command to add a Task to the task list.
 */
public class AddCommand extends Command {

    /** Task to be added to the task list */
    private Task task;

    /**
     * Creates a Command to add a Task.
     *
     * @param task The task to be added to the task list.
     */
    public AddCommand(Task task) {
        super();
        this.task = task;
    }

    /**
     * Adds the Task to the task list, saves the task list and
     * displays a message indicating completion of the command.
     *
     * @param tasks The task list.
     * @param storage The storage object that saves the task list.
     * @return A String that indicates that a task has been successfully added.
     * @throws DukeException If task contains an invalid date and time format.
     */
    @Override
    public String execute(Tasklist tasks, Storage storage) throws DukeException {
        try {
            tasks.add(task);
            storage.save(tasks);
            return "Got it. I've added this task:\n"
                    + "  " + task.toDisplayString() + "\n"
                    + "Now you have " + tasks.size() + " tasks in the list";
        } catch (DateTimeException dateTimeException) {
            throw new DukeException(ErrorMessage.INVALID_DATE_TIME_FORMAT.getMessage());
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
