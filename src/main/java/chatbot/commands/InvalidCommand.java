package chatbot.commands;

import chatbot.data.TaskList;
import chatbot.storage.Storage;
import chatbot.ui.Ui;

public class InvalidCommand extends Command {

    String message;

    public InvalidCommand(String message) {
        this.message = message;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return this.message;
    }
}
