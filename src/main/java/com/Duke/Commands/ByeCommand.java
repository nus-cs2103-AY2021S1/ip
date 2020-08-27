package com.Duke.Commands;

import com.Duke.ProcessManager.DukeException;
import com.Duke.ProcessManager.TaskList;
import com.Duke.ProcessManager.UI;

public class ByeCommand extends Command{

    private final TaskList ls;

    public ByeCommand(TaskList ls) {
        this.ls = ls;
    }

    @Override
    public boolean isDone(){
        return true;
    }

    public void execute(){
        try {
            UI.byeCalled(this.ls);
        }catch (DukeException e){
            UI.printError(e.toString());
        }
    }
}
