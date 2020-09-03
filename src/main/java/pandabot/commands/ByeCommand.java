package pandabot.commands;

import pandabot.storage.Storage;
import pandabot.tasks.TaskList;
import pandabot.ui.Ui;

/**
 * Represents a bye command which allows users to exit the program.
 */
public class ByeCommand extends Command {

    /**
     * Executes the bye command, where the program stops running and exits.
     * The user will be notified through printed messages by the ui.
     *  @param tasks the current TaskList object being used
     * @param ui the current Ui object being used
     * @param storage the current Storage object being used
     * @return the String representation to display
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.displayOnExit();
    }

    /**
     * Returns true if the program should exit.
     * Otherwise, returns false if the program should continue to run.
     *
     * @return true as the program exits on bye command
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
