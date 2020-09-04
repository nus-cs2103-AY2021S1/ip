package duke.command;

import duke.Storage;
import duke.TaskList;

/**
 * InvalidCommand class handles user inputs something invalid
 */

public class InvalidCommand extends Command {
    public InvalidCommand(String command, TaskList list, Storage storage) {
        super(command, list, storage);
    }

    /**
     * Informs user that his command was invalid
     * @param command User input
     * @param list TaskList object containing all tasks
     * @param storage Storage object that reads and writes to duke.ser
     */
    @Override
    public String execute(String command, TaskList list, Storage storage) {
        String horizontalLine = "____________________________________\n";
        return horizontalLine + Warnings.invalidCommandWarning() + horizontalLine;
    }
}
