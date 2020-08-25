package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents the Command where user exits the program.
 */
public class ByeCommand extends Command {
    /**
     * Renders good bye message.
     *
     * @param taskItems represents the tasks which is added to the task list.
     * @param ui        ui component which user interacts with or sees.
     * @param storage   Object for saving and loading tasks list to hard disk.
     */
    @Override
    public void execute(TaskList taskItems, Ui ui, Storage storage)  {
        ui.sayGoodbye();
    }

    /**
     * Returns instruction to Duke class to terminate programme.
     *
     * @return true to terminate program.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
