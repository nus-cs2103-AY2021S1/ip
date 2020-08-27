package duke;

/**
 * Represents a Command to display all Tasks from the task list.
 */
public class ListCommand extends Command {

    /**
     * Creates a Command to display all Tasks from the task list.
     */
    public ListCommand() {
        super();
    }

    /**
     * Displays all the tasks from the task list onto the user interface.
     *
     * @param tasks The task list.
     * @param ui The user interface.
     * @param storage The Storage object that saves the task list.
     * @throws DukeException If task number entered is invalid.
     */
    @Override
    public void execute(Tasklist tasks, Ui ui, Storage storage) {
        ui.display(tasks.toDisplayString());
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
