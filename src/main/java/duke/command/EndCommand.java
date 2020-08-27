package duke.command;

import duke.core.Storage;
import duke.core.TaskList;
import duke.core.Ui;

/**
 * Represents a Command to terminate the Duke programme.
 */
public class EndCommand extends Command{

    /**
     * Deactivates the given Ui object to signal the termination of the Duke programme.
     * @param tasks unused TaskList object
     * @param storage unused Storage object
     * @param ui Ui object to be deactivated.
     * @see duke.core.Duke
     */
    @Override
    public void execute (TaskList tasks, Storage storage, Ui ui) {
        System.out.println("Well I'll see you around, pardner!!");
        ui.setInactive();
    }
}
