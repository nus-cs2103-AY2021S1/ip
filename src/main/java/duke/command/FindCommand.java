package duke.command;

import main.java.duke.command.Command;
import main.java.duke.task.TaskList;
import duke.Ui;
import duke.Storage;

public class FindCommand extends Command {
    String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) {
        TaskList foundTasks = taskList.find(keyword);
        ui.showAllTasks(foundTasks);
    }

    public boolean isExit() {
        return false;
    }

}
