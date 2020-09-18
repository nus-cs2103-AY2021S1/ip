package duke.commands;

import duke.DukeException;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Command sub-type to define exiting the program
 */
public class ExitCommand extends Command {

    public static final String EXIT_FLAG = "EXIT";

    /**
     * Creates ExitCommand object.
     */
    public ExitCommand() {}

    @Override
    public boolean runCLI(TaskList taskList, Storage storage, Ui ui) {
        ui.exit();
        return false;
    }

    @Override
    public String runGUI(TaskList taskList, Storage storage, Ui ui) {
        return ExitCommand.EXIT_FLAG;
    }
}
