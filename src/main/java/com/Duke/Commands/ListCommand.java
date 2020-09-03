package com.Duke.Commands;

import com.Duke.TaskManager.DukeException;
import com.Duke.TaskManager.TaskList;
import com.Duke.TaskManager.UI;

public class ListCommand extends Command{

    private final TaskList ls;

    public ListCommand(TaskList ls) {
        this.ls = ls;
    }

    public void execute(){
        try {
            UI.listCalled(ls);
        }catch(DukeException e){
            UI.printError(e.toString());
        }
    }
}
