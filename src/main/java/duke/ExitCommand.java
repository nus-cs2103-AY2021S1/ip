package duke;

/**
 * Represents a Command to exit Duke.
 */
public class ExitCommand extends Command {

    /**
     * Creates a Command to exit Duke.
     */
    public ExitCommand() {
        super();
    }

    /**
     * Displays a goodbye message and exits Duke.
     *
     * @param tasks The task list.
     * @param ui The user interface.
     * @param storage The Storage object that saves the task list.
     */
    @Override
    public void execute(Tasklist tasks, Ui ui, Storage storage) {
        ui.showGoodbye();
        System.exit(0);
    }

    /**
     * Returns true as it is an instance of an ExitCommand.
     *
     * @return True.
     */
    @Override
    public boolean isExit() {
        return true;
    }

}
