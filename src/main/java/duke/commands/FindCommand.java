package duke.commands;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class FindCommand extends Command {
    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Finds tasks with the given keywords.
     *
     * @param tasks tasklist object.
     * @param ui Ui object.
     * @param storage Storage object.
     * @return a string of all tasks containing the keywords.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String output = ui.showTask(tasks, keyword);
        return output;
    }

}
