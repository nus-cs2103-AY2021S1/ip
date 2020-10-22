package duke.commands;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/** Represents the command that terminates the program when executed. */
public class ByeCommand extends Command {

    /** Prints out the ByeCommand message in Duke format.
     *
     * @param taskList The list of tasks.
     * @param ui The UI that prints out messages in Duke format.
     * @param storage The storage system that saves the taskList.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        setDialog(Ui.DIALOG_BYE);
    }

    /** Indicates that the program is terminating. */
    @Override
    public boolean isExit() {
        return true;
    }
}
