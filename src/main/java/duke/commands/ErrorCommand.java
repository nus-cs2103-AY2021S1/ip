package duke.commands;

import duke.Storage;
import duke.Ui;

public class ErrorCommand extends Command {
    final String errorMessage;
    public ErrorCommand(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * Prints out an error message.
     * @param ui a duke.Ui instance to enable calling of duke.Ui functions
     * @param storage a duke.Storage instance to enable calling of duke.Storage functions
     */
    @Override
    public String execute(Ui ui, Storage storage) {
        return ui.taskPrint(errorMessage);
    }
}
