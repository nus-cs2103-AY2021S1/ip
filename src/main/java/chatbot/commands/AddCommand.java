package chatbot.commands;

import chatbot.data.Task;
import chatbot.data.TaskList;
import chatbot.exception.ChatbotException;
import chatbot.storage.Storage;
import chatbot.ui.Ui;

public class AddCommand extends Command {

    private final Task toAdd;

    public AddCommand(Task task) {
        this.toAdd = task;
    }

    public Task getToAdd() {
        return this.toAdd;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws ChatbotException {
        assert toAdd != null : "Task is not supposed to be null";
        taskList.addTask(toAdd);

        boolean isSaveSuccess = storage.saveTasks(taskList.getTasks());
        assert isSaveSuccess : "Save tasks supposed to return true.";

        return ui.addSuccess(toAdd, taskList.count());
    }
}
