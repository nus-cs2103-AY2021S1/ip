package main.java.commands;

import main.java.data.TaskList;
import main.java.storage.Storage;
import main.java.ui.Ui;

public class FindCommand extends Command {

    String body;

    public FindCommand(String body) {
        this.body = body;
    }

    public boolean isExit() {
        return false;
    }

    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return ui.list(taskList.find(body));
    }
}
