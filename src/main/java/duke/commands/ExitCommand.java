package duke.commands;

import duke.DukeException;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Command sub-type to define exiting the program
 */
public class ExitCommand extends Command {

    /**
     * Creates ExitCommand object.
     */
    public ExitCommand() {}

    @Override
    public boolean run(TaskList taskList, Storage storage, Ui ui) {
        ui.exit();
        return false;
    }

    @Override
    public String runGUI(TaskList taskList, Storage storage, Ui ui) {
        System.exit(0);
        return ui.exit();
    }
}
