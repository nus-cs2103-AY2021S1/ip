package chatbot.commands;

import chatbot.common.Message;
import chatbot.data.TaskList;
import chatbot.storage.Storage;
import chatbot.ui.Ui;

public class ExitCommand extends Command {

    public boolean isExit() {
        return true;
    }

    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return ui.errorMessage(Message.EXIT_MESSAGE);
    }
}
