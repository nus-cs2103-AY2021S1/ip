package com.Duke.Commands;

import com.Duke.TaskManager.DukeException;
import com.Duke.TaskManager.TaskList;
import com.Duke.TaskManager.UI;

public class ByeCommand extends Command {

    private final TaskList taskList;

    public ByeCommand(TaskList taskList) {
        this.taskList = taskList;
    }

    @Override
    public boolean isDone() {
        return true;
    }

    public String execute() {
        try {
            return UI.byeCalled(taskList);
        } catch (DukeException e) {
            return UI.printError(e.toString());
        }
    }
}
