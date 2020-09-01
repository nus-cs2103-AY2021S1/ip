package duke.commands;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class FindCommand extends Command {
    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    public String execute(TaskList tasks, Ui ui, Storage storage) {

        String output = ui.showTask(tasks, keyword);
        return output;
    }

}
