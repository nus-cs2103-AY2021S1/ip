package main.java.com.jacob.duke.command;

import java.util.List;

import main.java.com.jacob.duke.Storage;
import main.java.com.jacob.duke.TaskList;
import main.java.com.jacob.duke.Ui;
import main.java.com.jacob.duke.task.Task;

public class PrintFilteredListCommand implements Command {
    private String inputCommand;
    private boolean isComplete;

    public PrintFilteredListCommand(String fullCommand) {
        this.inputCommand = fullCommand;
    }
    @Override
    public void execute(Ui ui, TaskList tasks, Storage storage) {
        List<Task> taskList = tasks.getTaskList();
        ui.showFilteredList(inputCommand, taskList);
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
