package com.jacob.duke.command;

import com.jacob.duke.Storage;
import com.jacob.duke.task.Task;
import com.jacob.duke.TaskList;
import com.jacob.duke.Ui;

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
