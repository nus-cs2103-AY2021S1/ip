package com.Duke.Commands;

import com.Duke.TaskManager.DukeException;
import com.Duke.TaskManager.TaskList;
import com.Duke.TaskManager.UI;

public class DeleteCommand extends Command{
    private final TaskList taskList;
    private final int taskIndex;

    public DeleteCommand(TaskList taskList, int taskIndex) {
        this.taskList = taskList;
        this.taskIndex = taskIndex;
    }

    @Override
    public String execute() {
        try{
            return UI.deleteCalled(taskList, taskIndex);
        }catch(DukeException e){
            return UI.printError(e.toString());
        }
    }
}
