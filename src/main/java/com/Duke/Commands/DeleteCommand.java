package com.Duke.Commands;

import com.Duke.ProcessManager.DukeException;
import com.Duke.ProcessManager.TaskList;
import com.Duke.ProcessManager.UI;

public class DeleteCommand extends Command{
    private final TaskList ls;
    private final int taskIndex;

    public DeleteCommand(TaskList ls, int taskIndex) {
        this.ls = ls;
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute() {
        try{
            UI.deleteCalled(ls, taskIndex);
        }catch(DukeException e){
            UI.printError(e.toString());
        }
    }
}
