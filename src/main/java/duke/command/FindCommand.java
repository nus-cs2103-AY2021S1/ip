package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/** This class represents the find command. */
public class FindCommand extends Command {

    /** The keyword used to filter the TaskList. */
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Saves the find message to the Ui.
     *
     * @param taskList The TaskList from which tasks will be filtered from.
     * @param ui The Ui that saves messages to be sent to the user.
     * @param storage The Storage object to make changes to.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.saveFindMessage(taskList, this.keyword);
    }
}
