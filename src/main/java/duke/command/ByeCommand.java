package duke.command;

import duke.Storage;
import duke.TaskList;

/**
 * ByeCommand class handles instructions when user wants to exit the script
 */

public class ByeCommand extends Command {
    public ByeCommand(String command, TaskList list, Storage storage) {
        super(command, list, storage);
    }

    /**
     * Runs the "bye" command
     * @param command User input
     * @param list TaskList object containing all tasks
     * @param storage Storage object that reads and writes to duke.ser
     */
    @Override
    public void execute(String command, TaskList list, Storage storage) {
        String horizontalLine = "____________________________________\n";
        System.out.println(horizontalLine + "Bye bye. See you soon bro!\n" + horizontalLine);
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
