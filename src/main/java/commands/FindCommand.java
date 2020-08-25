package commands;

import data.TaskList;
import storage.Storage;
import ui.Ui;

public class FindCommand extends Command {

    String body;

    public FindCommand(String body) {
        this.body = body;
    }

    public boolean isExit() {
        return false;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.list(taskList.find(body));
    }
}
