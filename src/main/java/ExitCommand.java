/**
 * Command to finish running the program
 */
public class ExitCommand extends Command {

    // Attributes
    private static final String EXIT = "Bye. Hope to see you again soon!";

    // Methods

    /**
     * Executes the command by saving the list of tasks to the computer and printing the result
     * @param tasks TaskList representing list of current tasks.
     * @param ui Ui object to handle printing of outputs.
     * @param storage Storage object to handle saving of outputs to computer
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        storage.save(tasks);
        ui.showMessage(EXIT + "\n File saved!");
    }

    /**
     * Returns whether the command is a command to exit.
     * @return true.
     */
    @Override
    boolean isExit() {
        return true;
    }
}
