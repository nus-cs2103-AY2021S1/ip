package main.java.com.jacob.duke.command;

import main.java.com.jacob.duke.*;
import main.java.com.jacob.duke.task.Task;

import java.util.List;

public class PrintFilteredListDateTimeCommand implements Command{
    public String inputCommand;
    private boolean isComplete;

    public PrintFilteredListDateTimeCommand(String fullCommand) {
        this.inputCommand = fullCommand;
    }
    @Override
    public void execute(Ui ui, TaskList tasks, Storage storage) throws DukeException {
        List<Task> taskList = tasks.taskList;
        ui.showFilteredDateTimeList(inputCommand, taskList);
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
