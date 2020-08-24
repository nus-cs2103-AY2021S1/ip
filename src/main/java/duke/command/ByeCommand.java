package duke.command;

import duke.storage.*;
import duke.tasklist.*;
import duke.ui.*;

/**
 * Represents an action to say bye to the user before exiting the programme.
 */
public class ByeCommand extends Command{

    /**
     * Constructs a <code>ByeCommand</code> object.
     */
    public ByeCommand() {
        super(true);
    }

    /**
     * Says bye to the user before exiting the programme.
     *
     * @param tasks TaskList to store Task.
     * @param ui Ui to interact with users.
     * @param storage Storage use by Duke to save and load files.
     * @return Nothing.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printMessage("Bye. Hope to see you again soon!");
        storage.save(tasks);
    }
}
