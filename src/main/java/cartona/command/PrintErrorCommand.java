package cartona.command;

import cartona.Storage;
import cartona.exception.CartonaException;
import cartona.task.TaskList;
import cartona.ui.Ui;

/**
 * The PrintErrorCommand, when executed prints an error message to the console.
 *
 * @author Jaya Rengam
 */
public class PrintErrorCommand implements Command {

    private boolean hasExecuted;

    /** The error message to be printed */
    private String errorMessage;

    PrintErrorCommand(String errorMessage) {
        this.hasExecuted = false;
        this.errorMessage = errorMessage;
    }

    /**
     * Prints the error message in the errorMessage field to the console.
     *
     * @param taskList The current TaskList in use
     * @param ui The Ui object that is used to print the action to the console.
     * @param storage The Storage object in use
     * @throws CartonaException if the command has already been executed.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws CartonaException {
        if (hasExecuted) {
            throw new CartonaException("Error: PrintErrorCommand already executed");
        }

        this.hasExecuted = true;

        return ui.getErrorMessageFormatted(errorMessage);
    }

    @Override
    public boolean isExitCmd() {
        return false;
    }
}

