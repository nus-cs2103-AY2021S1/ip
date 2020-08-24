package duke.command;

import duke.storage.*;
import duke.tasklist.*;
import duke.ui.*;

/**
 * Represents an action to list out the Task from TaskList.
 */
public class ListCommand extends Command{

    /**
     * Constructs a <code>ListCommand</code> object.
     */
    public ListCommand() {
        super(false);
    }

    /**
     * Lists out the Task in TaskList.
     *
     * @param tasks TaskList to store Task.
     * @param ui Ui to interact with users.
     * @param storage Storage use by Duke to save and load files.
     * @return Nothing.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printMessage(tasks.toString());
    }

}
