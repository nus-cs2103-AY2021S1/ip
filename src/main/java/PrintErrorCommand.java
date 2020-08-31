/**
 * The PrintErrorCommand, when executed prints an error message to the console.
 *
 * @author Jaya Rengam
 */
public class PrintErrorCommand implements Command {

    private boolean hasExecuted;
    private String errorMessage;

    PrintErrorCommand(String errorMessage) {
        this.hasExecuted = false;
        this.errorMessage = errorMessage;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws CartonaException {
        if (hasExecuted) {
            throw new CartonaException("Error: PrintErrorCommand already executed");
        }

        ui.printErrorMessage(errorMessage);

        this.hasExecuted = true;
    }

    @Override
    public boolean isExitCmd() {
        return false;
    }
}

