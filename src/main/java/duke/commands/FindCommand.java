package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class FindCommand implements Command {
    public String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    public boolean execute(TaskList tasks, Ui ui, Storage storage) {
        ui.filteredList(tasks.findTasks(keyword));
        return false;
    }
}
