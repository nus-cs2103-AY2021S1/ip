package chatbot.commands;

import chatbot.common.Message;
import chatbot.data.TaskList;
import chatbot.exception.ChatbotException;
import chatbot.storage.Storage;
import chatbot.ui.Ui;

public class InvalidCommand extends Command {

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws ChatbotException {
        return Message.INVALID_COMMAND;
    }
}
