package com.Duke.Commands;

import com.Duke.ProcessManager.DukeException;
import com.Duke.ProcessManager.TaskList;
import com.Duke.ProcessManager.UI;

public class ListCommand extends Command{

    private final TaskList ls;

    public ListCommand(TaskList ls) {
        this.ls = ls;
    }

    public void execute(){
        try {
            UI.listCalled(this.ls);
        }catch(DukeException e){
            UI.printError(e.toString());
        }
    }
}
