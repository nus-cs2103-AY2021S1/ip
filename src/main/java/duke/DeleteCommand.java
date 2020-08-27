package duke;

/**
 * Represents a Command to delete a Task from the task list.
 */
public class DeleteCommand extends Command {

    /** The index of the task to be deleted */
    private int taskNumber;

    /**
     * Creates a Command to delete a task.
     *
     * @param taskNumber The index of the task to be deleted.
     */
    public DeleteCommand(int taskNumber) {
        super();
        this.taskNumber = taskNumber;
    }

    /**
     * Deletes the task with the task number from the task list,
     * saves the task list and displays a message indicating
     * completion of the command.
     *
     * @param tasks The task list.
     * @param ui The user interface.
     * @param storage The Storage object that saves the task list.
     * @throws DukeException If task number entered is invalid.
     */
    @Override
    public void execute(Tasklist tasks, Ui ui, Storage storage) throws DukeException {
        try {
            Task removedTask = tasks.get(taskNumber);
            tasks.deleteTask(taskNumber);
            storage.save(tasks);
            ui.display("Noted. I've removed this task:\n" + "  "
                    + removedTask.toDisplayString() + "\n"
                    + "Now you have " + tasks.size() + " tasks in the list.");
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
