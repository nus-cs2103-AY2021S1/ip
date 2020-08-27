package com.Duke.Commands;

import com.Duke.ProcessManager.DukeException;
import com.Duke.ProcessManager.TaskList;
import com.Duke.ProcessManager.UI;

public class DoneCommand extends Command{

    private final TaskList ls;
    private final int taskIndex;

    public DoneCommand(TaskList ls, int taskIndex) {
        this.ls = ls;
        this.taskIndex = taskIndex;
    }

    public void execute(){
        try{
            UI.doneCalled(ls,taskIndex);
        }catch (DukeException e){
            UI.printError(e.toString());
        }catch(Exception e){
            UI.printError("     ☹ OOPS!!! There arent that many tasks in your list");
        }
    }
}
