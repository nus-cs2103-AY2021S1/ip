package duke;

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
     * @param ui The user interface.
     * @param storage The Storage object that saves the task list.
     * @throws DukeException If task number entered is invalid.
     */
    @Override
    public void execute(Tasklist tasks, Ui ui, Storage storage) throws DukeException {
        try {
            Task completedTask = tasks.get(taskNumber);
            completedTask.markAsDone();
            storage.save(tasks);
            ui.display("Nice! I've marked this task as done:\n" + "  "
                    + completedTask.toDisplayString());
        } catch (IndexOutOfBoundsException exception) {
            throw new DukeException("Please enter a valid task number!");
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
