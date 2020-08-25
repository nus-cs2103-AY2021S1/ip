package duke.commands;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/** Represents the command that shows the taskList. */
public class ListCommand extends Command {

    /** Prints out the DoneCommand message in Duke format.
     *
     * @param taskList The list of tasks.
     * @param ui The UI that prints out messages in Duke format.
     * @param storage The storage system that saves the taskList.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.formatLst(taskList.tasks);
    }
}
