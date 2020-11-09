package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents a command which ends the operation.
 */
public class ByeCommand extends Command {

    /**
     * Executes the operation for the program to terminate.
     *
     * @param tasks TaskList linked to the program.
     * @param ui Ui linked to the program.
     * @param storage Storage linked to the program.
     * @return String that contains the executed ByeCommand.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        assert storage != null;
        storage.save(tasks.getTasks());
        return ui.showBye();
    }

    /**
     * Returns a boolean that dictates if the program is running.
     *
     * @return True.
     */
    @Override
    public boolean isExit() {
        return true;
    }

}
