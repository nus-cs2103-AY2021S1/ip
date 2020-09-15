package chatbot.commands;

import chatbot.data.Task;
import chatbot.data.TaskList;
import chatbot.exception.ChatbotException;
import chatbot.storage.Storage;
import chatbot.ui.Ui;

/**
 * Represents a command to either delete or mark task as done given a type.
 */

public class DeleteCommand extends Command {

    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index + 1;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws ChatbotException {

        Task deletedTask = taskList.removeTask(index);

        assert storage.saveTasks(taskList.getTasks()) : "Save tasks supposed to return true.";

        return ui.deleteSuccess(deletedTask, taskList.count());
    }
}
