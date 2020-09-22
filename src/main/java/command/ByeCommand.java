package command;

import storage.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;

import java.util.List;

public class ByeCommand extends Command {
    public ByeCommand(String command) {
        super(command);
    }

    public String execute(TaskList tasks, Ui ui, Storage storage) {
        List<Task> taskList = tasks.getTasks();

        String message = "";

        isExit = true;
        message = message + "  Bye. Hope to see you again soon!";

        return message;
    }
}
