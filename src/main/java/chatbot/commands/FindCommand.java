package chatbot.commands;

import chatbot.data.TaskList;
import chatbot.storage.Storage;
import chatbot.ui.Ui;

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
