package main.java.com.jacob.duke.command;

import main.java.com.jacob.duke.Storage;
import main.java.com.jacob.duke.task.Task;
import main.java.com.jacob.duke.TaskList;
import main.java.com.jacob.duke.Ui;

import java.util.List;

public class PrintListCommand implements Command {
    public boolean isComplete = false;
    @Override
    public void execute(Ui ui, TaskList tasks, Storage storage) {
        List<Task> taskList = tasks.taskList;
        ui.showFullList(taskList);
        isComplete = true;
    }

    @Override
    public boolean isComplete() {
        return isComplete;
    }

    @Override
    public boolean isBye() {
        return false;
    }
}
