package duke.commands;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/** Represents the command that shows TaskList's list. */
public class ListCommand extends Command {

    /** Prints out the DoneCommand message in Duke format.
     *
     * @param tasks The list of tasks.
     * @param ui The UI that prints out messages in Duke format.
     * @param storage The storage system that saves the list of tasks.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.formatLst(tasks.lst);
    }
}
