package duke.command;

import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

public class FormatCommand extends Command {
    public FormatCommand() {
    }

    /**
     * Shows format that users can follow to input commands.
     *
     * @param storage Storage data of tasks in hard disk.
     * @param taskList TaskList where task actions are done.
     * @param ui Ui that shows format that users can follow to input commands.
     */
    @Override
    public void execute(Storage storage, TaskList taskList, Ui ui) {
        ui.showFormat();
    }
}
