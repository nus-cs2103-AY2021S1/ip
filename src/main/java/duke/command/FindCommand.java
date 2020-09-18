package duke.command;

import duke.command.Command;
import duke.task.TaskList;
import duke.Ui;
import duke.Storage;

public class FindCommand extends Command {
    String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    public String execute(TaskList taskList, Ui ui, Storage storage) {
        TaskList foundTasks = taskList.find(keyword);
        return ui.showAllTasks(foundTasks);
    }

    public boolean isExit() {
        return false;
    }

}
