package duke.commands;

import duke.Storage;
import duke.Ui;

/**
 * Encapsulates a Command to exit the client.
 */
public class ExitCommand extends Command {
    /**
     * Terminates the application
     */
    @Override
    public String execute(Ui ui, Storage storage) {
        String returnStr = ui.byeMessage();
        return returnStr;
    }
}
