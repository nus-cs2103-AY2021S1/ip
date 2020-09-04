package duke.command;

import duke.Storage;
import duke.TaskList;

/**
 * ByeCommand class handles instructions when user wants to exit the script.
 */

public class ByeCommand extends Command {
    public ByeCommand(String command, TaskList list, Storage storage) {
        super(command, list, storage);
    }

    /**
     * Runs the "bye" command.
     * @param command User input
     * @param list TaskList object containing all tasks
     * @param storage Storage object that reads and writes to duke.ser
     * @return A bye response
     */
    @Override
    public String execute(String command, TaskList list, Storage storage) {
        String horizontalLine = "____________________________________\n";
        String byeMessage = "Bye bye. See you soon bro!\n";
        return horizontalLine + byeMessage + horizontalLine;
    }
}
