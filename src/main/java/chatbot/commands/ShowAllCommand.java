package chatbot.commands;

import chatbot.data.TaskList;
import chatbot.storage.Storage;
import chatbot.ui.Ui;

public class ShowAllCommand extends Command {

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {

        return ui.list(taskList.getTasks());
    }
}
