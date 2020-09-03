package main.java.commands;

import main.java.common.Type;

import main.java.data.Task;
import main.java.data.TaskList;

import main.java.exception.ChatbotException;
import main.java.storage.Storage;
import main.java.ui.Ui;

/**
 * Represents a command to either delete or mark task as done given a type.
 */

public class ActionCommand extends Command {

    Type type;
    String body;

    public ActionCommand(Type type, String body) {
        this.body = body;
        this.type = type;
    }

    public boolean isExit() {
        return false;
    }

    public String execute(TaskList taskList, Ui ui, Storage storage) throws ChatbotException {

        int index;
        String response = "";

        try {
            index = Integer.parseInt(body) - 1;
        } catch (NumberFormatException e) {
            throw new ChatbotException("Please give me a valid number.");
        }

        switch (type) {
        case DELETE:
            Task deletedTask = taskList.removeTask(index);
            response = ui.deleteSuccess(deletedTask, taskList.count());
            break;
        case DONE:
            Task taskDone = taskList.markAsDone(index);
            response = ui.markDoneSuccess(taskDone);
            break;
        default:
            break;
        }

        storage.saveTasks(taskList.getTasks());

        return response;
    }
}
