package duke.commands;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/** Represents the command that terminates the program when executed. */
public class ByeCommand extends Command {

    /** Prints out the ByeCommand message in Duke format.
     *
     * @param tasks The list of tasks.
     * @param ui The UI that prints out messages in Duke format.
     * @param storage The storage system that saves the list of tasks.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showBye();
    }

    /** Indicates that the program is terminating. */
    @Override
    public boolean isExit() {
        return true;
    }
}
