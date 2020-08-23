package com.jacob.duke.command;

import com.jacob.duke.*;
import com.jacob.duke.task.Task;

import java.util.List;

public class PrintFilteredListCommand implements Command{
    public String inputCommand;
    private boolean isComplete;

    public PrintFilteredListCommand(String fullCommand) {
        this.inputCommand = fullCommand;
    }
    @Override
    public void execute(Ui ui, TaskList tasks, Storage storage) throws DukeException {
        List<Task> taskList = tasks.taskList;
        ui.showFilteredList(inputCommand,taskList);
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
