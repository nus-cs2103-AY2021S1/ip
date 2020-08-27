package duke;

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
     * @param ui The user interface.
     * @param storage The storage object that saves the task list.
     * @throws DukeException If task contains an invalid date and time format.
     */
    @Override
    public void execute(Tasklist tasks, Ui ui, Storage storage) throws DukeException {
        try {
            tasks.add(task);
            storage.save(tasks);
            ui.display("Got it. I've added this task:\n"
                    + "  " + task.toDisplayString() + "\n"
                    + "Now you have " + tasks.size() + " tasks in the list");
        } catch (DateTimeException dateTimeException) {
            throw new DukeException("Please enter a valid date and time in the format 'DD-MM-YYYY HHMM'!");
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
