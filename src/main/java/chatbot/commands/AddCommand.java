package chatbot.commands;

import chatbot.common.Message;

import chatbot.data.Task;
import chatbot.data.TaskList;

import chatbot.exception.ChatbotException;
import chatbot.storage.Storage;
import chatbot.ui.Ui;

public class AddCommand extends Command {

    private Task toAdd;

    public AddCommand(Task task) {
        this.toAdd = task;
    }

    public boolean isExit() {
        return false;
    }

    public String execute(TaskList taskList, Ui ui, Storage storage) throws ChatbotException {

        if (taskList.addTask(toAdd)) {
            String response = ui.addSuccess(toAdd, taskList.count());
            return response;
        }

        assert storage.saveTasks(taskList.getTasks()) : "Save tasks supposed to return true.";

        return Message.ADD_FAIL;
    }
}
