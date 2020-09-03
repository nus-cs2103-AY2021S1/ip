package duke.commands;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

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
    public String runNew(TaskList taskList, Storage storage, Ui ui) throws DukeException {
        return ui.exit();
    }
}
