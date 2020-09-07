package duke.commands;

import duke.Storage;
import duke.Ui;

/**
 * Encapsulates a Command to print an error.
 */
public class ErrorCommand extends Command {
    final String errorMessage;

    /**
     * Initializes an instance of ErrorCommand.
     * @param errorMessage String input that will be printed out as the error message.
     */
    public ErrorCommand(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * Prints out an error message to display to the client.
     * @param ui a duke.Ui instance to enable calling of duke.Ui functions
     * @param storage a duke.Storage instance to enable calling of duke.Storage functions
     * @return String being printed.
     */
    @Override
    public String execute(Ui ui, Storage storage) {
        return ui.taskPrint(errorMessage);
    }
}
