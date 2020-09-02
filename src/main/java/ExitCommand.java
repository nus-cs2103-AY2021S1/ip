/**
 * The ExitCommand class runs the steps required before termination of the program.
 *
 * @author Jaya Rengam
 */
public class ExitCommand implements Command {
    private boolean hasExecuted;

    ExitCommand() {
        this.hasExecuted = false;
    }

    /**
     * Prints the exit message to console.
     *
     * @param taskList the running TaskList
     * @param ui the Ui object that is used to print the action to the console
     * @param storage the Storage object in use
     * @throws CartonaException
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws CartonaException {
        // Check if the command has already been executed.
        if (hasExecuted) {
            throw new CartonaException("Error: PrintErrorCommand already executed");
        }

        // Print UI message
        ui.printExitMessage();

        this.hasExecuted = true;
    }

    @Override
    public boolean isExitCmd() {
        return true;
    }
}