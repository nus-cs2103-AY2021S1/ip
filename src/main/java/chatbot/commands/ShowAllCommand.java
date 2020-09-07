package chatbot.commands;

import chatbot.data.TaskList;

import chatbot.storage.Storage;
import chatbot.ui.Ui;

public class ShowAllCommand extends Command {

    public boolean isExit() {
        return false;
    }

    public String execute(TaskList taskList, Ui ui, Storage storage) {

        String response = ui.list(taskList.getTasks());

        return response;
    }
}
