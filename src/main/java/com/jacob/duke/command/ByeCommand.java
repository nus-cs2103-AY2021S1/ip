package com.jacob.duke.command;

import com.jacob.duke.*;
import com.jacob.duke.task.Task;

import java.util.List;

public class ByeCommand implements Command{
    boolean isComplete = false;

    @Override
    public void execute(Ui ui, TaskList tasks, Storage storage) throws DukeException {
        List<Task> taskList = tasks.taskList;
        ui.sayBye();
        isComplete = true;
    }

    @Override
    public boolean isComplete() {
        return isComplete;
    }

    @Override
    public boolean isBye() {
        return true;
    }
}
