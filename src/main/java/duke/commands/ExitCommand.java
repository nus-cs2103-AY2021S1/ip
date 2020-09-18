package duke.commands;

import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Command sub-type to define exiting the program.
 */
public class ExitCommand extends Command {

    public static final String EXIT_FLAG = "EXIT";

    /**
     * Create ExitCommand object.
     */
    public ExitCommand() {}

    /**
     * Exits the program.
     *
     * @param taskList TaskList object handling the current list
     * @param storage Storage object to read/write from disk
     * @param ui Ui object to handle user interface interactions
     * @return false, end program loop
     */
    @Override
    public boolean runCLI(TaskList taskList, Storage storage, Ui ui) {
        ui.exit();
        return false;
    }

    /**
     * Exits the program.
     *
     * @param taskList TaskList object handling the current list
     * @param storage Storage object to read/write from disk
     * @param ui Ui object to handle user interface interactions
     * @return Flag to indicate system exit
     */
    @Override
    public String runGUI(TaskList taskList, Storage storage, Ui ui) {
        return ExitCommand.EXIT_FLAG;
    }
}
