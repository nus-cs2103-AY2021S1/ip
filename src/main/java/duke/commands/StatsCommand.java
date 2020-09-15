package duke.commands;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/** Represents the command that shows the statistics of the taskList. */
public class StatsCommand extends Command {

    /** Prints out the StatsCommand message in Duke format.
     *
     * @param taskList The list of tasks.
     * @param ui The UI that prints out messages in Duke format.
     * @param storage The storage system that saves the taskList.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        setDialog(ui.formatStats(taskList.getTasks()));
    }
}
