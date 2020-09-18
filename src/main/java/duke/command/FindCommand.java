package main.java.duke.command;

import main.java.duke.command.Command;
import main.java.duke.task.TaskList;
import main.java.duke.Ui;
import main.java.duke.Storage;

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
