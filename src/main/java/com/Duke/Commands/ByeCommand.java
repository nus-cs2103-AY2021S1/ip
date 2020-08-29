package com.Duke.Commands;

import com.Duke.TaskManager.DukeException;
import com.Duke.TaskManager.TaskList;
import com.Duke.TaskManager.UI;

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
            UI.byeCalled(ls);
        }catch (DukeException e){
            UI.printError(e.toString());
        }
    }
}
