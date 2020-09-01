package main.java.commands;

import main.java.common.Type;
import main.java.data.Task;
import main.java.data.TaskList;
import main.java.storage.Storage;
import main.java.ui.Ui;

import java.time.LocalDate;
import java.util.ArrayList;

public class ShowCommand extends Command {

    Type type;
    String body;

    public ShowCommand(Type type, String body) {
        this.type = type;
        this.body = body;
    }

    public boolean isExit() {
        return false;
    }

    public String execute(TaskList taskList, Ui ui, Storage storage) {

        String response = "";

        switch (type) {
        case LIST:
            response = ui.list(taskList.getTasks());
            break;
        case DATE:
            ArrayList<Task> tasks = taskList.retrieveTasksOnDate(
                    LocalDate.parse(body));
            response = ui.list(tasks);
            break;
        default:
            break;
        }

        return response;
    }
}
