package chatbot.commands;

import chatbot.common.Message;
import chatbot.data.TaskList;
import chatbot.storage.Storage;
import chatbot.ui.Ui;

public class ExitCommand extends Command {

    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return ui.errorMessage(Message.MESSAGE_EXIT);
    }
}
